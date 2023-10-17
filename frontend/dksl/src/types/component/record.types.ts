// 서버에서 받아오는 전체 레코드 데이터 (전적 + 프로필)
interface IRecordData {
  profile: IProfileData[];
  match_histories: IRecordDetailData[];
}

// 프로필 데이터
interface IProfileData {
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
interface IRecordDetailData {
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
  gold: number;
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

// Pie Graph Data Type
interface IPieData {
  id: string;
  label: string;
  value: number;
}

// RecordBodyComponent Props Type
interface RecordBodyProps {
  recorddata: IRecordData | null;
  piedata: IPieData[];
  tab: number;
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