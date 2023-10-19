// React
import { useEffect, useState, useCallback } from 'react';
import { useLocation } from 'react-router-dom';
// Swal
import Swal from 'sweetalert2';
import withReactContent from 'sweetalert2-react-content';
// Component
import HeaderComponent from '../components/common/HeaderComponent';
import GroupMainComponent from '../components/group/GroupMainComponent';
import GroupCreateComponent from '../components/group/GroupCreateComponent';
import GroupDetailComponent from '../components/group/GroupDetailComponent';
import LoadingComponent from '../components/common/LoadingComponent';
// Service
import {
  getGroupList,
  setNewGroup,
  searchGroup,
  groupDetail,
  joinGroup,
} from '../services/GroupService';
// Jotai
import { useAuth } from '../jotai/auth';

const MySWal = withReactContent(Swal);

const GroupContainer = () => {
  const [teamList, setTeamList] = useState(null);
  const [detailList, setDetailList] = useState(null);
  const [path, setPath] = useState(null);
  const auth = useAuth();
  const url = useLocation();

  useEffect(() => {
    const fetchAllGroupData = async () => {
      const data = await getGroupList();
      setTeamList((prevTeamList) => {
        if (prevTeamList === data) {
          return prevTeamList;
        }
        return data;
      });
    };

    const fetchDetailGroupData = async (name) => {
      const data = await groupDetail(name, auth ? true : false);

      const current = data.data.chairman;
      
      data.data.summonerResponse = data.data.summonerResponse.filter((e) => {
        if (e.name == current) {
          data.data.currentSummoner = e;
        } else return e;
      });

      setDetailList((prevDetailList) => {
        if (prevDetailList === data.data) {
          return prevDetailList;
        }
        return data.data;
      });
    };

    setPath(url.pathname);

    if (url.pathname == '/group/main' && teamList == null) {
      fetchAllGroupData();
    } else if (url.pathname == '/group/detail') {
      fetchDetailGroupData(url.search.split('=')[1]);
    }
  }, [url.pathname, teamList]);

  const getByteToImage = useCallback((imgSrc) => {
    const binaryString = atob(imgSrc);
    const bytes = new Uint8Array(binaryString.length);

    for (let i = 0; i < binaryString.length; i++) {
      bytes[i] = binaryString.charCodeAt(i);
    }

    const img = new Blob([bytes], {
      type: 'image/jpg',
    });

    return URL.createObjectURL(img);
  }, []);

  const onSearch = useCallback(async (word) => {
    const data = await searchGroup(word);

    if (data.status == 200) {
      Swal.fire({
        title: '검색 완료',
        text: '검색 요청이 완료되었습니다.',
        icon: 'success',
        iconColor: '#6E8387',
        confirmButtonColor: '#6E8387',
        confirmButtonText: '확인',
      });
      setTeamList({
        ...teamList,
        teamList: data.data,
      });
    }
  }, []);

  const getNewGroup = useCallback(() => {
    MySWal.fire({
      title: '<b>&#129309; 소속 만들기</b>',
      html: <GroupCreateComponent />,
      width: '60%',
      heightAuto: true,
      padding: '2.5% 5%',
      confirmButtonColor: 'var(--maincolor-depth1)',
      confirmButtonText: '생성 요청',
      focusConfirm: false,
      preConfirm: async () => {
        const formData = new FormData();
        const blob = new Blob(
          [
            JSON.stringify({
              name: document.getElementById('name').value,
              description: document.getElementById('description').value,
            }),
          ],
          {
            type: 'application/json',
          }
        );

        formData.append('team', blob);
        formData.append('img', document.getElementById('img').files[0]);

        const data = await setNewGroup(formData);
        if (data) {
          Swal.fire({
            title: '요청 성공',
            text: '소속 생성이 요청되었습니다.',
            iconColor: 'var(--maincolor-depth1)',
            icon: 'success',
            confirmButtonColor: 'var(--maincolor-depth1)',
            confirmButtonText: '확인',
          });
        } else {
          Swal.fire({
            title: '요청 실패',
            text: '소속 생성이 잘못되었습니다.',
            icon: 'error',
            confirmButtonColor: 'var(--maincolor-depth1)',
            confirmButtonText: '확인',
          });
        }
        return data;
      },
    });
  }, []);

  const onJoinGroup = useCallback(async () => {
    Swal.fire({
      icon: 'info',
      title: `${detailList.name}에 가입하시겠습니까?`,
      showDenyButton: true,
      confirmButtonText: '확인',
      denyButtonText: `취소`,
    }).then(async (result) => {
      if (result.isConfirmed) {
        const data = await joinGroup(detailList.name);

        if (data === true) {
          Swal.fire({
            icon: 'success',
            title: `${detailList.name}에 가입되셨습니다.`,
          }).then(() => {
            location.reload();
          });
        } else {
          Swal.fire('가입을 실패했습니다.', '', 'error');
        }
      } else if (result.isDenied) {
        Swal.fire('가입이 취소 되었습니다.', '', 'info');
      }
    });
  }, [detailList]);

  return path == '/group/main' ? (
    <>
      <HeaderComponent />
      <GroupMainComponent
        groupList={teamList}
        createGroup={getNewGroup}
        onSearch={onSearch}
        getByteToImage={getByteToImage}
      />
    </>
  ) : detailList ? (
    <>
      <HeaderComponent />
      <GroupDetailComponent
        auth={auth}
        detailList={detailList}
        getByteToImage={getByteToImage}
        onJoinGroup={onJoinGroup}
      />
    </>
  ) : (
    <LoadingComponent white={false} />
  );
};

export default GroupContainer;
