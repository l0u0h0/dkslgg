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

export type RecentAnalyzeDataType = {
  win: number;
  lose: number;
  kill: number;
  death: number;
  assist: number;
  kda: number;
  kill_participation: number;
  champions: [
    {
      name: string;
      odds: number;
      kda: number;
      games: number;
    }
  ];
  positions: [
    {
      percent: number;
      name: string;
    }
  ];
};

export type DuoPlayerDataType = {
  name: string;
  game: number;
  win: number;
  lose: number;
  odds: number;
};

export type RankDataType = {
  solo_tier: string;
  free_tier: string;
  tft_tier: string;
};

export type RecordDataType = {
  win_or_lose: number; // 0: win, 1: lose
  game_type: number;
  game_duration: string; // 게임 진행 시간
  game_time: string; // ex_1시간 전.
  champ_name: string;
  level: number;
  spell_0_id: string;
  spell_1_id: string;
  rune_0_id: string;
  rune_1_id: string;
  kill: number;
  death: number;
  assist: number;
  kda: number;
  position: string;
  kill_participation: number;
  cs: number;
  vision: number;
  item_0_id: number;
  item_1_id: number;
  item_2_id: number;
  item_3_id: number;
  item_4_id: number;
  item_5_id: number;
  item_6_id: number;
  teams: [
    {
      win_or_lose: number;
      players: [
        {
          name: string;
          champ_name: string;
          level: number;
          spell_0_id: string;
          spell_1_id: string;
          rune_0_id: string;
          rune_1_id: string;
          kill: number;
          death: number;
          assist: number;
          kda: number;
          position: string;
          gold: number;
          cs: number;
          vision: number;
          total_damage_dealt_to_champions: number; // 딜량
          damage_taken_on_team_percentage: number; // 딜퍼센테이지
          item_0_id: number;
          item_1_id: number;
          item_2_id: number;
          item_3_id: number;
          item_4_id: number;
          item_5_id: number;
          item_6_id: number;
        }
      ];
    }
  ];
};

export type RecordContainerType = {
  rank: RankDataType;
  duo_player: DuoPlayerDataType[];
  recent_analyze: RecentAnalyzeDataType;
  record: RecordDataType[];
};

export type GetRecordDataType = {
  profile: ProfileDataType;
  record: RecordContainerType;
};
