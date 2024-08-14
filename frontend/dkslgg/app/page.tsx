import SearchContainer from "./components/Main/Search/SearchContainer";

export default async function Home() {
  return (
    <main className="w-full h-full flex flex-col">
      <SearchContainer />
    </main>
  );
}
