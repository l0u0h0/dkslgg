export type ProfileDataType = {
  name: string;
  tier: string;
  profile_icon_id: number;
  last_updated_at: string;
};

// 가공 전 프로필 데이터
export type OriginProfileDataType = {
  summoner_name: string;
  profile_icon_id: number;
  tier_name: string;
  queue_id: number;
  rank: number;
  wins: number;
  losses: number;
  current_season_summary_id: string;
  last_updated_at: string;
  champions: [
    {
      queue_id: number;
      cnt: number;
      win_rate: number;
      kda: number;
      champion_name: string;
    }
  ];
  positions: [
    {
      queue_id: number;
      img: string | null;
      win_rate: number;
      kda: number;
      cnt: number;
      line: string;
    }
  ];
};
