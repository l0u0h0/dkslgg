// Jotai
import { atom, useAtomValue, useSetAtom } from 'jotai';
import { atomWithDefault } from 'jotai/utils';
// Service
import { getSummonerGroup } from '../services/GroupService';

const getGroup = async (name) => {
  if (name == null || name == undefined || typeof name != 'string') {
    return null;
  }

  const data = await getSummonerGroup(name);

  if (data == 'NoData') return 'NoData';
  if (data != undefined && data.status == 200) {
    return data.data;
  } else {
    return null;
  }
};

const groupAtom = atomWithDefault(getGroup);

const updateGroupAtom = atom(null, async (get, set, update) => {
  set(groupAtom, await getGroup(update));
});

export const useUpdateGroup = () => useSetAtom(updateGroupAtom);

export const useGroup = () => useAtomValue(groupAtom);
