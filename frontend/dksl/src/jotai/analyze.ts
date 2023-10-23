// jotai
import { Getter, atom, useAtomValue, useSetAtom } from 'jotai';
import { atomWithDefault } from 'jotai/utils';
// Service
import { getAnalyzeData } from '../services/RecordService';
// types
import { IAnalyzeData, AnalyzeData } from '../types/component/analyze.types';

const capitalizeStr = (string: string) => {
  if (string === 'Jarvaniv') return 'JarvanIV';
  return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase();
};

const getAnalyze = async (
  name: string |  null
): Promise<IAnalyzeData | 'NoData'> => {
  if (name == null || typeof name !== 'string')
    return 'NoData';
  if (name) {
    const data: AnalyzeData | null = await getAnalyzeData(name);

    if (data) {
      return {
        chapmions: [
          capitalizeStr(data.champ0.replace(/\s+/g, '')),
          capitalizeStr(data.champ1.replace(/\s+/g, '')),
          capitalizeStr(data.champ2.replace(/\s+/g, '')),
        ],
        celeb: {
          name: data.celeb,
          url: data.url,
          line: data.line,
          desc: data.desc,
        },
        cluster: {
          no: data.cluster_no,
          name: data.name,
          receive: data['받은 피해'],
          cs: data['10분 미니언'],
          dpm: data.dpm,
          kda: data.kda,
          minion_avg: `${data.minion_avg}`,
          assist: data['분 당 어시'],
          soloKill: data['솔로킬'],
          vision: data['시야 점수'],
          dealt: data['챔피언 딜량'],
          level: data['챔피언 레벨'],
          ward: data['핑크와드 구매'],
        },
      };
    }
  } else {
    return 'NoData';
  }

  return 'NoData';
};

const anaylzeAtom = atomWithDefault(getAnalyze(null));

const updateAnalyzeAtom = atom(null, async (_get, set, update: string | null) => {
  set(anaylzeAtom, await getAnalyze(update));
});

export const useUpdateAnalyze = () => useSetAtom(updateAnalyzeAtom);

export const useAnalyze = () => useAtomValue(anaylzeAtom);
