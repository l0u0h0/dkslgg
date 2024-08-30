import SearchContainer from "@/app/components/Search/SearchContainer";
import ProfileContainer from "@/app/components/Search/ProfileContainer";
import { HOST } from "@/config";

interface IParams {
  params: {
    user: string;
  };
}

export async function generateMetadata({ params }: IParams) {
  const user = decodeURI(params.user);
  return {
    title: `${user} - 전적 조회`,
    description: `소환사 ${user}의 정보 조회 페이지`,
  };
}

const getPlayerRecord = async ({ params }: IParams) => {
  const response = await (
    await fetch(
      `${HOST}/api/record/getPlayerRecord?user=${decodeURI(params.user)}`
    )
  ).json();

  return response.data.data;
};

export default async function User({ params }: IParams) {
  const data = await getPlayerRecord({ params });

  console.log(data.profile);
  return (
    <main className="w-full h-full">
      <ProfileContainer profile={data.profile} />
      <SearchContainer />
    </main>
  );
}
