from typing import Optional, Union
from app.common.crud import CRUDBase
from sqlmodel.ext.asyncio.session import AsyncSession
from sqlmodel import select
from uuid import UUID

from app.users.model import LolProfiles
from app.users.schema import (
    ILolProfilesCreate,
    ILolProfilesUpdate,
)

class CRUDLolProfiles(CRUDBase[LolProfiles, ILolProfilesCreate, ILolProfilesUpdate]):
    def get(
        self, *, puu_id: str, db_session: Optional[AsyncSession] = None
    ) -> Optional[LolProfiles]:
        db_session = db_session
        query = select(self.model).where(self.model.puu_id == puu_id)
        response = db_session.execute(query)
        return response.scalar_one_or_none()


lol_profiles = CRUDLolProfiles(LolProfiles)