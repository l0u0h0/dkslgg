import { useState, useEffect } from 'react';
// Component
import HeaderComponent from '../components/common/HeaderComponent';
import RankingComponent from '../components/main/RankingComponent';
import SearchComponent from '../components/main/SearchComponent';
// Service
import { getGroupRankData, getRankData } from '../services/MainService';

const MainContainer = () => {
  const [hofTab, setHofTab] = useState(0);
  const [rankTab, setRankTab] = useState(0);
  const [rankData, setRankData] = useState(null);
  const [groupRankData, setGroupRankData] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      const rankData = await getRankData();
      if (!rankData) return;
      
      const dividedRankData = [];

      for (let i = 0; i < rankData.length; i += 10) {
        dividedRankData.push(rankData.slice(i, i + 10));
      }

      setRankData(dividedRankData);
    };

    fetchData();

    const groupRankData = async () => {
      const data = await getGroupRankData();
      setGroupRankData(data);
    };

    groupRankData();
  }, []);

  return (
    <>
      <HeaderComponent />
      <SearchComponent />
      <RankingComponent
        hofTab={hofTab}
        setHofTab={setHofTab}
        hofData={rankData}
        rankTab={rankTab}
        setRankTab={setRankTab}
        rankData={groupRankData}
      />
    </>
  );
};

export default MainContainer;
