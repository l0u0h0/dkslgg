from datetime import datetime, timedelta
from typing import Optional
from sqlalchemy.sql import func

from sqlmodel import Field, Relationship
from app.common.model import BaseIdModel, BaseLolProfile, SQLModel

from app.database import Base

class LolProfiles(BaseLolProfile, table=True):
    __tablename__ = "LOL_PROFILES"
    profile_icon_id: int = Field(
        nullable=False, description="해당 lol profile이 설정한 프로필 아이콘 고유번호"
    )

    region: str =  Field(
        max_length=5,
        description="해당 롤 계정의 지역 정보"
    )

    summoner_id: str = Field(
        max_length=47,
        unique=True,
        description="라이엇 API에서 랭크 정보 불러오기 위함"
    )

    summoner_level: int = Field(
        nullable=False, description="롤 계정 레벨"
    )

    summoner_name: str = Field(
        max_length=20,
        description="롤 계정의 소환사 이름"
    )

    last_updated_at: datetime = Field(nullable=False)

