from typing import Dict
from sqlmodel import Field, SQLModel

from app.common.model import BaseIdModel


class MatchHistoriesBase(SQLModel):
    level: int = Field(nullable=False, description="해당 match 내에서 user의 level.")
    CS: int = Field(
        nullable=False,
        description="creep score의 약자로, 해당 match에서 user의 CS.",
    )
    item_0_id: int = Field(
        nullable=False,
        default=0,
        description="해당 match에서 user의 첫번째 item칸에 있는 item의 고유 번호.",
    )
    item_1_id: int = Field(
        nullable=False,
        default=0,
        description="해당 match에서 user의 두번째 item칸에 있는 item의 고유 번호.",
    )
    item_2_id: int = Field(nullable=False, default=0)
    item_3_id: int = Field(nullable=False, default=0)
    item_4_id: int = Field(nullable=False, default=0)
    item_5_id: int = Field(nullable=False, default=0)
    item_6_id: int = Field(nullable=False, default=0)
    spell_0_id: int = Field(
        nullable=False,
        default=0,
        description="해당 match에서 user가 사용한 첫 번째 spell의 고유 번호.",
    )
    spell_1_id: int = Field(nullable=False, default=0)
    rune_0_id: int = Field(
        nullable=False,
        default=0,
        description="해당 match에서 user가 사용한 첫 번째 rune의 고유 번호.",
    )
    rune_1_id: int = Field(nullable=False, default=0)
    season: str = Field(
        max_length=20,
        nullable=False,
        description="match가 발생한 season을 나타낸다. 어느 season에서 발생한 것인지 알 수 있다.",
    )
    gold: int = Field(nullable=False, description="해당 match에서 user가 획득한 총 gold 양.")
    play_duration: str = Field(nullable=False, description="match의 play 총 시간. ")
    play_time: str = Field(nullable=False, description="match가 발생한 날짜 정보.")
    queue_type: int = Field(
        nullable=False, description="해당 match의 type(자유 랭크인지 솔로 랭크인지) 에 대한 고유 번호."
    )
    summoner_name: str = Field(
        max_length=20, nullable=False, description="각 match의 참여한 개별 소환사의 이름을."
    )
    match_id: str = Field(
        max_length=100, nullable=False, description="라이엇 API 가 제공하는 해당 match 의 고유 번호."
    )
    line_name: str = Field(
        max_length=20, nullable=False, description="해당 match에서 user가 플레이한 line 이름. "
    )
    champion_name_en: str = Field(
        max_length=20,
        nullable=False,
        description="해당 match에서 user가 플레이한 champion 이름.",
    )
    kill: int = Field(nullable=False, description="해당 match에서 user가 kill한 횟수.")
    death: int = Field(nullable=False, description="해당 match에서 user가 death한 횟수.")
    assist: int = Field(nullable=False, description="해당 mathc에서 user가 assist한 횟수.")
    kda: float
    kill_participation: float
    control_wards_placed: int
    total_damage_dealt_to_champions: int
    damage_taken_on_team_percentage: float
    vision_score: int
    win_or_lose: int = Field(
        nullable=False, description="해당 match에서 user가 이겼는지 졌는지를 나타내는 요소."
    )

class MatchHistories(BaseIdModel, MatchHistoriesBase, table=True):
    __tablename__ = "MATCH_HISTORIES"


class CurrentSeasonSummariesBase(SQLModel):
    __tablename__ = "CURRENT_SEASON_SUMMARIES"
    losses: int = Field(
        nullable=False,
        default=0,
        description="해당 current season summary의 시즌 전체 패배 횟수.",
    )
    lp: int = Field(
        unique=True,
        nullable=False,
        default=0,
        description="해당 current season summary의 league point.",
    )
    queue_id: int = Field(
        nullable=False, description="해당 Season Summary가 속한 queue 유형의 고유 번호."
    )
    rank: int = Field(
        nullable=False,
        description="해당 Current Season Summary의 tier를 세분화한 단계이다. ( I ~ IV )",
    )
    summoner_id: str = Field(
        max_length=47,
        nullable=False,
        description="라이엇 API에서 제공하는 소환사를 구별하기 위한 고유 번호. 라이엇 API의 league 정보를 불러오는데 쓰이는 고유번호.  해당 Current Season Summary의 소환사를 가리킨다.",
    )
    puu_id: str = Field(
        max_length=78,
        primary_key=True,
        nullable=False,
        description="라이엇 API에서 제공하는 글로벌한 소환사의 고유 번호. lol profile을 구분하기 위한 필수 요소. 또한 라이엇 API에서 Match ID를 불러오는데 쓰이는 고유번호.",
    )
    tier_name: str = Field(
        nullable=False, description="해당 Season Summary가 속한 tier의 고유 번호."
    )
    wins: int = Field(
        nullable=False, description="해당 current season summary의 시즌 전체 승리 횟수."
    )


class CurrentSeasonSummaries(CurrentSeasonSummariesBase, table=True):
    pass

class CurrentSeasonSummariesFlexBase(SQLModel):
    __tablename__ = "CURRENT_SEASON_SUMMARIES_FLEX"
    losses: int = Field(
        nullable=False,
        default=0,
        description="해당 current season summary의 시즌 전체 패배 횟수.",
    )
    lp: int = Field(
        unique=True,
        nullable=False,
        default=0,
        description="해당 current season summary의 league point.",
    )
    queue_id: int = Field(
        nullable=False, description="해당 Season Summary가 속한 queue 유형의 고유 번호."
    )
    rank: int = Field(
        nullable=False,
        description="해당 Current Season Summary의 tier를 세분화한 단계이다. ( I ~ IV )",
    )
    summoner_id: str = Field(
        max_length=47,
        nullable=False,
        description="라이엇 API에서 제공하는 소환사를 구별하기 위한 고유 번호. 라이엇 API의 league 정보를 불러오는데 쓰이는 고유번호.  해당 Current Season Summary의 소환사를 가리킨다.",
    )
    puu_id: str = Field(
        max_length=78,
        primary_key=True,
        nullable=False,
        description="라이엇 API에서 제공하는 글로벌한 소환사의 고유 번호. lol profile을 구분하기 위한 필수 요소. 또한 라이엇 API에서 Match ID를 불러오는데 쓰이는 고유번호.",
    )
    tier_name: str = Field(
        nullable=False, description="해당 Season Summary가 속한 tier의 고유 번호."
    )
    wins: int = Field(
        nullable=False, description="해당 current season summary의 시즌 전체 승리 횟수."
    )

class CurrentSeasonSummariesFlex(CurrentSeasonSummariesFlexBase, table=True):
    pass


class MostLineSummariesBase(SQLModel):
    __tablename__ = "MOST_LINE_SUMMARIES"
    id: int = Field(
        primary_key=True,
        nullable=False
    )
    current_season_summary_id: str = Field(
        max_length=78,
    )
    line_name: str = Field()
    kda: float = Field()
    win_rate: float = Field()
    count: int = Field()


class MostLineSummaries(MostLineSummariesBase, table=True):
    pass


class MostChampionBase(SQLModel):
    __tablename__ = "MOST_CHAMPION_SUMMARIES"
    id: int = Field(
        primary_key=True,
        nullable=False
    )
    current_season_summary_id: str = Field(
        max_length=78,
    )
    champion_name: str = Field()
    kda: float = Field()
    win_rate: float = Field()
    count: int = Field()


class MostChampion(MostChampionBase, table=True):
    pass
