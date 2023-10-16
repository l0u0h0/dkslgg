from datetime import datetime
from riotwatcher import ApiError
from app.apis.riot.controller import RiotApiController
from app.common.schema import IResponseBase, create_response
from app.common.utils import as_dict
from fastapi import APIRouter, Body, Depends, HTTPException
from app.users.crud import lol_profiles as lol_profiles_crud
from app.database import exec_query, get_db


router = APIRouter()


@router.get("")
def get_challengers():
    riot_api = RiotApiController(summoner_name="톰 클랜시")

    challengers = riot_api.get_challengers_info()

    # 'entries' 키를 사용하여 항목들에 접근
    entries = challengers.get("entries", [])

    # 'leaguePoints'를 기준으로 내림차순 정렬
    sorted_challengers = sorted(entries, key=lambda x: int(x["leaguePoints"]), reverse=True)

    # 상위 30개 항목 선택
    top_30_challengers = sorted_challengers[:30]

    # 'leaguePoints'와 'summonerName'만 추출
    result = [{"summonerName": entry["summonerName"], "leaguePoints": int(entry["leaguePoints"])} for entry in top_30_challengers]

    return result

