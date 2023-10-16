from datetime import datetime
from typing import Optional, Union
from pydantic import BaseModel

from app.common.model import BaseLolProfile

class ILolProfilesCreate(BaseModel):
    puu_id: str
    profile_icon_id: int
    region: str
    summoner_id: str
    summoner_level: int
    summoner_name: str
    user_id: str
    last_updated_at: datetime


class ILolProfilesUpdate(BaseLolProfile):
    pass