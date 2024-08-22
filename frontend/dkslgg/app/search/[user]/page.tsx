interface IParams {
  params: {
    user: string;
  };
}

export async function generateMetadata({ params }: IParams) {
  const user = params.user;
  return {
    title: `${user}`,
    description: `소환사 ${user}의 정보 조회 페이지`,
  };
}

export default async function User({ params }: IParams) {
  console.log(params);
  return <main className="w-full h-full"></main>;
}
