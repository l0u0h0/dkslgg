import os
from pydantic import BaseSettings, PostgresDsn, validator, EmailStr, AnyHttpUrl
from typing import Optional, Dict, Any, Union, List
import secrets

class Settings(BaseSettings):
    APP_ENV: str
    RDS_HOSTNAME: str
    RDS_PORT: str
    RDS_DB_NAME: str
    RDS_USERNAME: str
    RDS_PASSWORD: str

    @property
    def DB_URL(self) -> str:
        return f"mysql+pymysql://{self.RDS_USERNAME}:{self.RDS_PASSWORD}@{self.RDS_HOSTNAME}:{self.RDS_PORT}/{self.RDS_DB_NAME}"

    RIOT_AP_ID: str
    RIOT_API_URL_KR: str

    RIOT_API_KEY: str

    class Config:
        case_sensitive = True
        env_file = (
                os.path.join(
                    os.path.dirname(os.path.abspath(__file__)), os.pardir, os.pardir
                )
                + "/.env"
        )


settings = Settings()