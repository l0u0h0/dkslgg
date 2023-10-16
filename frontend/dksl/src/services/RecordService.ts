// User API (Axios)
import Swal from 'sweetalert2';
import { record } from './api';

const getSearchData = async (word: string) => {
  try {
    const postRequest = await record.post(
      `/match-histories?summoner_name=${word}`
    );
    if (postRequest.status != 200) throw new Error('POST ERROR');
    const putRequest = await record.put(
      `/match-histories?summoner_name=${word}`
    );
    if (putRequest.status != 200) throw new Error('PUT ERROR');
    const response = await record.get(`/match-histories?summoner_name=${word}`);
    if (response.status != 200) {
      return 'NoData';
    }
    return response.data;
  } catch (error) {
    Swal.fire({
      title: '이런!',
      text: '전적 조회 중 오류 발생!',
      icon: 'error',
    }).then((result) => {
      if (result.isConfirmed) window.location.href = '/';
    });
  }
};

const getAnalyzeData = async (name: string) => {
  // return {
  //   "champ0": "Camille",
  //   "champ1": "Jax",
  //   "champ2": "Garen",
  //   "celeb": "Thal",
  //   "line": "TOP",
  //   "desc": "전 프로게이머 출신으로 트런들 장인으로 유명하다. 라인전을 강하게 하는 타입이 아닌, 한타 능력과 게임 센스가 뛰어난 아웃복서형 탑솔러다.",
  //   "url": "https://www.youtube.com/@thalyoutube877",
  //   "minion_avg": 23.8,
  //   "cluster_no": "1",
  //   "name": "날렵한 격투가",
  //   "챔피언 레벨": "3",
  //   "솔로킬": "3",
  //   "dpm": "3",
  //   "받은 피해": "1",
  //   "kda": "2",
  //   "10분 미니언": "4",
  //   "챔피언 딜량": "3",
  //   "시야 점수": "1",
  //   "핑크와드 구매": "2",
  //   "분 당 어시": "3"
  // }
  try {
    const response = await record.get(`/recommend?summoner_name=${name}`);
    if (response.status == 200) {
      return response.data;
    }
    return 'NoData';
  } catch (error) {
    console.error(error);
  }
};

export { getSearchData, getAnalyzeData };
