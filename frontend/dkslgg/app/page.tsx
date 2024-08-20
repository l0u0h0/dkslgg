import SearchContainer from "./components/Main/Search/SearchContainer";
import TaggingContainer from "./components/Main/Tagging/TaggingContainer";

export default async function Home() {
  return (
    <main className="w-full h-full flex flex-col">
      <SearchContainer />
      <TaggingContainer />
    </main>
  );
}
