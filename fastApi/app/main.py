from pydantic import BaseModel, Field
from fastapi import Depends, FastAPI, Request
from fastapi.middleware.cors import CORSMiddleware
from requests import Session
from sqlalchemy import select

# Routes
from app.match_history.router import router as match_history_router
from app.recommend.router import router as recommend_router
from app.users.router import router as challenger_router

# Models
# * DB 생성 전 import 하여 불러오기
from app.common.model import *
from app.match_history.model import *
# DB config
from app.database import Base, engine, get_db

app = FastAPI()

# DB Create
Base.metadata.create_all(bind=engine)

# attach routers
app.include_router(match_history_router, prefix="/match-histories", tags=["유저 매치 히스토리"])
app.include_router(recommend_router, prefix="/recommend", tags=["추천 시스템"])
app.include_router(challenger_router, prefix="/challengers", tags=["챌린저 목록"])

origins = [
    "https://j9a703.p.ssafy.io"
]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

@app.get("/")
async def root(request: Request):
    url_list = [
        {"path": route.path, "name": route.name} for route in request.app.routes
    ]
    return url_list
