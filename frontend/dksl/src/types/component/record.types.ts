// 서버에서 받아오는 전체 레코드 데이터 (전적 + 프로필)
export interface IRecordData {
  profile: IProfileData[];
  match_histories: IRecordDetailData[];
}

// 프로필 데이터
export interface IProfileData {
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
    },
  ];
  positions: [
    {
      queue_id: number;
      img: string | null;
      win_rate: number;
      kda: number;
      cnt: number;
      line: string;
    },
  ];
}

// 전적 데이터
export interface IRecordDetailData {
  level: number;
  CS: number;
  item_0_id: number;
  item_1_id: number;
  item_2_id: number;
  item_3_id: number;
  item_4_id: number;
  item_5_id: number;
  item_6_id: number;
  spell_0_id: number;
  spell_1_id: number;
  rune_0_id: number;
  rune_1_id: number;
  season: string;
  gold: string;
  play_duration: string;
  play_time: string;
  queue_type: number;
  summoner_name: string;
  match_id: string;
  line_name: string;
  champion_name_en: string;
  kill: number;
  death: number;
  assist: number;
  kda: number;
  kill_participation: number;
  control_wards_placed: number;
  total_damage_dealt_to_Champions: number;
  damage_taken_on_team_percentage: number;
  vision_score: number;
  win_or_lose: number;
}

// 스펠 타입
export interface Spell {
  type: string;
  version: string;
  data: { [key: number]: SpellData };
}

// 스펠 데이터 타입
export interface SpellData {
  id: string;
  name: string;
  description: string;
  tooltip: string;
  maxrank: number;
  cooldown: number[];
  cooldownBurn: string;
  cost: number[];
  costBurn: string;
  datavalues: {};
  effect: (number[] | null)[];
  effectBurn: (string | null)[];
  vars: [];
  key: string;
  summonerLevel: number;
  modes: string[];
  costType: string;
  maxammo: string;
  range: number[];
  rangeBurn: string;
  image: {
    full: string;
    sprite: string;
    group: string;
    x: number;
    y: number;
    w: number;
    h: number;
  };
  resource: string;
}

// Formatting Record Data
<<<<<<< HEAD
export interface IFormatRecordData {
=======
interface IFormatRecordData {
>>>>>>> 133a5ff660a23a5ec27f269ea52bc5648c4b3b82
  profile: {
    positions_cnt: number;
    last_updated_at: string;
    summoner_name: string;
    profile_icon_id: number;
    tier_name: string;
    queue_id: number;
    rank: number;
    wins: number;
    losses: number;
    current_season_summary_id: string;
    champions: [
      {
        queue_id: number;
        cnt: number;
        win_rate: number;
        kda: number;
        champion_name: string;
      },
    ];
    positions: [
      {
        queue_id: number;
        img: string | null;
        win_rate: number;
        kda: number;
        cnt: number;
        line: string;
      },
    ];
  };
  freeRank: IProfileData | null;
  duoPlayer: any[][];
  recent: IRecentDataType;
  match_histories: {
    win: number;
    cur: undefined;
    summary: {
      name: string;
      champ: string;
    }[][];
    data: IRecordDetailData[];
    winner: IRecordDetailData[];
    loser: IRecordDetailData[];
  }[];
}

// Pie Graph Data Type
export interface IPieData {
  id: string;
  label: string;
  value: number;
}

// RecordBodyComponent Props Type
<<<<<<< HEAD
export interface RecordBodyProps {
=======
interface RecordBodyProps {
>>>>>>> 133a5ff660a23a5ec27f269ea52bc5648c4b3b82
  recorddata: IFormatRecordData | null;
  piedata: IPieData[];
  tab: number;
  searchSummonerName: string | undefined;
  setTab: React.Dispatch<React.SetStateAction<number>>;
  leaveTeam: (name: string) => Promise<void>;
  getByteToImage: (imgSrc: string) => string;
  fetchChampData: (championName: string) => Promise<{
    en_name: string;
    name: any;
    title: any;
    tags: any;
    tips: any;
  } | null>;
}

// Record Jotai Service Data Type
export interface IRecentDataType {
  win: number;
  lose: number;
  count: number;
  kill: number;
  death: number;
  assist: number;
  score: number;
  kill_participation: number;
  line: [];
}

export interface IRecordFormatData {
  profile: IProfileData[];
  match_histories: IRecordDetailData[][];
}

<<<<<<< HEAD
// getDuoPlayer Props Type
export interface GetDuoPlayerDataProps {
=======
// getDuoPlayer Props Type 
interface GetDuoPlayerDataProps {
>>>>>>> 133a5ff660a23a5ec27f269ea52bc5648c4b3b82
  win: number;
  cur: number | undefined;
  summary: {
    name: string;
    champ: string;
  }[][];
  data: IRecordDetailData[];
  winner: IRecordDetailData[];
  loser: IRecordDetailData[];
<<<<<<< HEAD
}
=======
}
>>>>>>> 133a5ff660a23a5ec27f269ea52bc5648c4b3b82
