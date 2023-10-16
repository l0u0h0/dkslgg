# FrontEnd README

## 개발 서버 구동 방법

1. ### pnpm 설치 안되어있을 시

> `npm install -g pnpm` 선행

- ### window에서 사용 시 이런 오류가 발생한다면

> pnpm : 이 시스템에서 스크립트를 실행할 수 없으므로 파일을 로드할 수 없습니다. 자세한 내용은 aboutExecution
> Policies(https://go.microsoft.com/fwlink/?LinkID=135170)를 참조하십시오.
> 위치 줄:1 문자:1
> +pnpm
> +
> +CategoryInfo : 보안 오류: (:) PSSecurityExcep

1. windows PowerShell을 관리자 권한으로 실행
2. Get-ExecutionPolicy 명령어 사용해 본인의 권한 상태 확인
3. 권한이 RemoteSigned가 아니라면 Set-ExecutionPolicy RemoteSigned 를 입력
4. Get-ExecutionPolicy 를 다시 사용하면 RemoteSigned로 변경 화인

- [링크 참조](https://velog.io/@ariel1031/Next.js-%EC%8B%9C%EC%9E%91%ED%95%98%EA%B8%B0)


2. ### node_modules 디렉토리가 루트에 없을 시

> `pnpm install` 선행

3. ### 리액트 개발 서버 띄우기

> 루트 디렉토리로 이동한 후 `pnpm dev` 실행

4. ### 개발 서버 종료 시

> 커맨드 창에 q를 지그시 눌러주기

5. ### 테스트 코드 실행해볼 때

> `pnpm test` 커맨드를 실행  



## 프로젝트 구조

- `config` : jest 포맷팅 설정파일
- `src/pages` : 하나의 라우트, (하나의 페이지)를 담당하는 컴포넌트. 컨테이너에서 데이터 요청 로직 구현
- `src/components` : 컨테이너를 구성하는 기능 단위의 컴포넌트들.
UI나 데이터 가공 및 처리와 UI는 여기서 담당.
- `src/jotai` : Jotai 상태관리 로직 구현한 파일들 모아놓는 디렉
- `src/services` : API 요청 및 Jotai를 활용한 데이터 가공 등 데이터 처리에 대한 로직 들 모으자!
- `src/__mocks__` : svgr 목업을 위한 파일
- `src/__tests__` : Jest 테스팅 파일 모아놓
- `src/styles` : Styled-Components 컴포난트 파일들 모아놓는

```
📦versionTest
 ┣ 📂config
 ┃ ┗ 📂jest
 ┃ ┃ ┣ 📜cssTransform.cjs
 ┃ ┃ ┣ 📜fileTransform.cjs
 ┃ ┃ ┗ 📜setupTests.js
 ┣ 📂public
 ┃ ┣ 📂assets
 ┃ ┃ ┣ 📜react.svg
 ┃ ┃ ┗ 📜vite.svg
 ┣ 📂src
 ┃ ┣ 📂atoms
 ┃ ┃ ┗ 📜InputAtom.jsx
 ┃ ┣ 📂components
 ┃ ┃ ┣ 📜CalendarComponent.jsx
 ┃ ┃ ┣ 📜MainComponent.jsx
 ┃ ┃ ┣ 📜TestChartComponent.jsx
 ┃ ┃ ┗ 📜TestComponent.jsx
 ┃ ┣ 📂pages
 ┃ ┃ ┣ 📜MainContainer.jsx
 ┃ ┃ ┗ 📜TestContainer.jsx
 ┃ ┣ 📂services
 ┃ ┃ ┗ 📜testService.js
 ┃ ┣ 📂jotai
 ┃ ┃ ┗ 📜testCount.js
 ┃ ┣ 📂styles
 ┃ ┃ ┗ 📂common
 ┃ ┃ ┃ ┗ 📜main.style.js
 ┃ ┣ 📂__mocks__
 ┃ ┃ ┗ 📜svgrMock.js
 ┃ ┣ 📂__tests__
 ┃ ┃ ┣ 📂__snapshots__
 ┃ ┃ ┃ ┗ 📜App.test.jsx.snap
 ┃ ┃ ┗ 📜App.test.jsx
 ┃ ┣ 📜App.css
 ┃ ┣ 📜App.jsx
 ┃ ┣ 📜data.js
 ┃ ┣ 📜index.css
 ┃ ┗ 📜main.jsx
 ┣ 📜.eslintrc.cjs
 ┣ 📜.gitignore
 ┣ 📜.prettierrc.json
 ┣ 📜.swcrc
 ┣ 📜index.html
 ┣ 📜jest.config.cjs
 ┣ 📜package.json
 ┣ 📜pnpm-lock.yaml
 ┣ 📜README.md
 ┗ 📜vite.config.js
 ```

 ---

## package.json

```JSON
{
  "name": "dksl",
  "private": true,
  "version": "0.0.1",
  "type": "module",
  "scripts": {
    "dev": "vite",
    "build": "vite build",
    "lint": "eslint . --ext js,jsx --report-unused-disable-directives --max-warnings 0",
    "preview": "vite preview",
    "test": "jest --transformIgnorePatterns \"node_modules/(?!axios)/\""
  },
  "dependencies": {
    "@nivo/calendar": "^0.83.0",
    "@nivo/core": "^0.83.0",
    "@nivo/pie": "^0.83.0",
    "@nivo/radar": "^0.83.0",
    "axios": "^1.5.0",
    "jotai": "^2.4.1",
    "react": "^18.2.0",
    "react-dom": "^18.2.0",
    "react-error-boundary": "^4.0.11",
    "react-router-dom": "^6.15.0",
    "react-select": "^5.7.4",
    "styled-components": "^6.0.7",
    "sweetalert2": "^11.7.27",
    "sweetalert2-react-content": "^5.0.7"
  },
  "devDependencies": {
    "@swc/core": "^1.3.82",
    "@swc/jest": "^0.2.29",
    "@testing-library/jest-dom": "^6.1.2",
    "@testing-library/react": "^14.0.0",
    "@testing-library/user-event": "^14.4.3",
    "@types/jest": "^29.5.4",
    "@types/react": "^18.2.15",
    "@types/react-dom": "^18.2.7",
    "@types/styled-components": "^5.1.27",
    "@vitejs/plugin-react-swc": "^3.3.2",
    "eslint": "^8.45.0",
    "eslint-plugin-react": "^7.32.2",
    "eslint-plugin-react-hooks": "^4.6.0",
    "eslint-plugin-react-refresh": "^0.4.3",
    "identity-obj-proxy": "^3.0.0",
    "jest": "^27.5.1",
    "jest-environment-jsdom": "^27.5.1",
    "jest-watch-typeahead": "^2.2.2",
    "prettier": "^3.0.3",
    "vite": "^4.4.5"
  },
  "main": "vite.config.js"
}

```
