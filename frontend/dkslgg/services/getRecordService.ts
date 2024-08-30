import { OriginProfileDataType } from "@/types/record.type";

export const getProfileData = (data: OriginProfileDataType) => {
  const newData = {
    name: data.summoner_name,
    tier: data.tier_name,
    profile_icon_id: data.profile_icon_id,
    last_updated_at: data.last_updated_at,
  };

  return newData;
};
