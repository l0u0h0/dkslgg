-- Tendency
INSERT INTO `tendency`(`id`, `name`, `initial`) VALUES ('challenging', '#도전적인', 'C');
INSERT INTO `tendency`(`id`, `name`, `initial`) VALUES ('stable', '#안정적인', 'S');
INSERT INTO `tendency`(`id`, `name`, `initial`) VALUES ('vintage', '#올드한', 'V');
INSERT INTO `tendency`(`id`, `name`, `initial`) VALUES ('mz', '#MZ한', 'M');
INSERT INTO `tendency`(`id`, `name`, `initial`) VALUES ('early', '#초반형', 'E');
INSERT INTO `tendency`(`id`, `name`, `initial`) VALUES ('lately', '#후반형', 'L');
INSERT INTO `tendency`(`id`, `name`, `initial`) VALUES ('detector', '#철거반', 'D');
INSERT INTO `tendency`(`id`, `name`, `initial`) VALUES ('killer', '#장의사', 'K');

-- LBTI
    INSERT INTO `lbti` ( `name`, `description`, `champion_name`, `first_tendency_id`, `second_tendency_id`, `third_tendency_id`, `fourth_tendency_id`)
    VALUES ('본인으로 직접 야바위하는 오공', '다이브나 암살 같은 하이 리스크 하이 리턴을 좋아하는 당신!\n 챔프 폭이 넓지 않고, 내가 좋아하는 챔피언이 너프를 당해도 쉽사리 놓지 못하는 성격입니다.\n\n초반 라인전에 특히나 힘을 쓰고,\n결국 승리를 위한 게임을 즐기는 성향이군요!', 'Wukong', 'challenging', 'vintage', 'early', 'detector');
    INSERT INTO `lbti` ( `name`, `description`, `champion_name`, `first_tendency_id`, `second_tendency_id`, `third_tendency_id`, `fourth_tendency_id`)
    VALUES ('적챔으로 무한힐 아트록스', '다이브나 암살 같은 하이 리스크 하이 리턴을 좋아하는 당신!\n 챔프 폭이 넓지 않고, 내가 좋아하는 챔피언이 너프를 당해도 쉽사리 놓지 못하는 성격입니다.\n\n초반 라인전에 특히나 힘을 쓰고,\n결국 본인의 캐리로 게임을 승리로 이끄는 성향이군요!', 'Aatrox', 'challenging', 'vintage', 'early', 'killer');
    INSERT INTO `lbti` ( `name`, `description`, `champion_name`, `first_tendency_id`, `second_tendency_id`, `third_tendency_id`, `fourth_tendency_id`)
    VALUES ('얼음공주 트런들', '다이브나 암살 같은 하이 리스크 하이 리턴을 좋아하는 당신!\n 챔프 폭이 넓지 않고, 내가 좋아하는 챔피언이 너프를 당해도 쉽사리 놓지 못하는 성격입니다.\n\n후반 왕귀에 특히나 힘을 쓰고,\n결국 승리를 위한 게임을 즐기는 성향이군요!', 'Trundle', 'challenging', 'vintage', 'lately', 'detector');
    INSERT INTO `lbti` ( `name`, `description`, `champion_name`, `first_tendency_id`, `second_tendency_id`, `third_tendency_id`, `fourth_tendency_id`)
    VALUES ('앞구르기 뒷점멸 베인', '다이브나 암살 같은 하이 리스크 하이 리턴을 좋아하는 당신!\n 챔프 폭이 넓지 않고, 내가 좋아하는 챔피언이 너프를 당해도 쉽사리 놓지 못하는 성격입니다.\n\n후반 왕귀에 특히나 힘을 쓰고,\n결국 본인의 캐리로 게임을 승리로 이끄는 성향이군요!', 'Vayne', 'challenging', 'vintage', 'lately', 'killer');
    INSERT INTO `lbti` ( `name`, `description`, `champion_name`, `first_tendency_id`, `second_tendency_id`, `third_tendency_id`, `fourth_tendency_id`)
    VALUES ('고담시티 징크스', '다이브나 암살 같은 하이 리스크 하이 리턴을 좋아하는 당신!\n 챔프 폭이 넓고, 상향된 챔피언은 그때 그때 즐겨야 하는 성격입니다.\n\n초반 라인전에 특히나 힘을 쓰고,\n결국 승리를 위한 게임을 즐기는 성향이군요!', 'Jinx', 'challenging', 'mz', 'early', 'detector');
    INSERT INTO `lbti` ( `name`, `description`, `champion_name`, `first_tendency_id`, `second_tendency_id`, `third_tendency_id`, `fourth_tendency_id`)
    VALUES ('르블랑', '다이브나 암살 같은 하이 리스크 하이 리턴을 좋아하는 당신!\n 챔프 폭이 넓고, 상향된 챔피언은 그때 그때 즐겨야 하는 성격입니다.\n\n초반 라인전에 특히나 힘을 쓰고,\n결국 본인의 캐리로 게임을 승리로 이끄는 성향이군요!', 'Leblanc', 'challenging', 'mz', 'early', 'killer');
    INSERT INTO `lbti` ( `name`, `description`, `champion_name`, `first_tendency_id`, `second_tendency_id`, `third_tendency_id`, `fourth_tendency_id`)
    VALUES ('빽도 마스터 마스터이', '다이브나 암살 같은 하이 리스크 하이 리턴을 좋아하는 당신!\n 챔프 폭이 넓고, 상향된 챔피언은 그때 그때 즐겨야 하는 성격입니다.\n\n후반 왕귀에 특히나 힘을 쓰고,\n결국 승리를 위한 게임을 즐기는 성향이군요!', 'MasterYi', 'challenging', 'mz', 'lately', 'detector');
    INSERT INTO `lbti` ( `name`, `description`, `champion_name`, `first_tendency_id`, `second_tendency_id`, `third_tendency_id`, `fourth_tendency_id`)
    VALUES ('수학의 정석 요네', '다이브나 암살 같은 하이 리스크 하이 리턴을 좋아하는 당신!\n 챔프 폭이 넓고, 상향된 챔피언은 그때 그때 즐겨야 하는 성격입니다.\n\n후반 왕귀에 특히나 힘을 쓰고,\n결국 본인의 캐리로 게임을 승리로 이끄는 성향이군요!', 'Yone', 'challenging', 'mz', 'lately', 'killer');

    INSERT INTO `lbti` ( `name`, `description`, `champion_name`, `first_tendency_id`, `second_tendency_id`, `third_tendency_id`, `fourth_tendency_id`)
    VALUES ('임시 포탑용 아지르', '허깅이나 한타 같은 안정적인 플레이를 좋아하는 당신!\n 챔프 폭이 넓지 않고, 내가 좋아하는 챔피언이 너프를 당해도 쉽사리 놓지 못하는 성격입니다.\n\n초반 라인전에서는 더욱 집중하고,\n결국 승리를 위한 게임을 즐기는 성향이군요!', 'Azir', 'stable', 'vintage', 'early', 'detector');
    INSERT INTO `lbti` ( `name`, `description`, `champion_name`, `first_tendency_id`, `second_tendency_id`, `third_tendency_id`, `fourth_tendency_id`)
    VALUES ('진혼곡으로 한입 하는 카서스', '허깅이나 한타 같은 안정적인 플레이를 좋아하는 당신!\n 챔프 폭이 넓지 않고, 내가 좋아하는 챔피언이 너프를 당해도 쉽사리 놓지 못하는 성격입니다.\n\n초반 라인전에서는 더욱 집중하고,\n결국 본인의 캐리로 게임을 승리로 이끄는 성향이군요!', 'Karthus', 'stable', 'vintage', 'early', 'killer');
    INSERT INTO `lbti` ( `name`, `description`, `champion_name`, `first_tendency_id`, `second_tendency_id`, `third_tendency_id`, `fourth_tendency_id`)
    VALUES ('16렙의 약속 케일', '허깅이나 한타 같은 안정적인 플레이를 좋아하는 당신!\n 챔프 폭이 넓지 않고, 내가 좋아하는 챔피언이 너프를 당해도 쉽사리 놓지 못하는 성격입니다.\n\n후반 한타에서는 더욱 집중하고,\n결국 승리를 위한 게임을 즐기는 성향이군요!', 'Kayle', 'stable', 'vintage', 'lately', 'detector');
    INSERT INTO `lbti` ( `name`, `description`, `champion_name`, `first_tendency_id`, `second_tendency_id`, `third_tendency_id`, `fourth_tendency_id`)
    VALUES ('가로등 무한 돌리기 잭스', '허깅이나 한타 같은 안정적인 플레이를 좋아하는 당신!\n 챔프 폭이 넓지 않고, 내가 좋아하는 챔피언이 너프를 당해도 쉽사리 놓지 못하는 성격입니다.\n\n후반 한타에서는 더욱 집중하고,\n결국 본인의 캐리로 게임을 승리로 이끄는 성향이군요!', 'Jax', 'stable', 'vintage', 'lately', 'killer');
    INSERT INTO `lbti` ( `name`, `description`, `champion_name`, `first_tendency_id`, `second_tendency_id`, `third_tendency_id`, `fourth_tendency_id`)
    VALUES ('트리스타나', '허깅이나 한타 같은 안정적인 플레이를 좋아하는 당신!\n 챔프 폭이 넓고, 상향된 챔피언은 그때 그때 즐겨야 하는 성격입니다.\n\n초반 라인전에서는 더욱 집중하고,\n결국 승리를 위한 게임을 즐기는 성향이군요!', 'Tristana', 'stable', 'mz', 'early', 'detector');
    INSERT INTO `lbti` ( `name`, `description`, `champion_name`, `first_tendency_id`, `second_tendency_id`, `third_tendency_id`, `fourth_tendency_id`)
    VALUES ('당구장 초크도둑 리신', '허깅이나 한타 같은 안정적인 플레이를 좋아하는 당신!\n 챔프 폭이 넓고, 상향된 챔피언은 그때 그때 즐겨야 하는 성격입니다.\n\n초반 라인전에서는 더욱 집중하고,\n결국 본인의 캐리로 게임을 승리로 이끄는 성향이군요!', 'LeeSin', 'stable', 'mz', 'early', 'killer');
    INSERT INTO `lbti` ( `name`, `description`, `champion_name`, `first_tendency_id`, `second_tendency_id`, `third_tendency_id`, `fourth_tendency_id`)
    VALUES ('팔방 Q 난사 제리', '허깅이나 한타 같은 안정적인 플레이를 좋아하는 당신!\n 챔프 폭이 넓고, 상향된 챔피언은 그때 그때 즐겨야 하는 성격입니다.\n\n후반 한타에서는 더욱 집중하고,\n결국 승리를 위한 게임을 즐기는 성향이군요!', 'Zeri', 'stable', 'mz', 'lately', 'detector');
    INSERT INTO `lbti` ( `name`, `description`, `champion_name`, `first_tendency_id`, `second_tendency_id`, `third_tendency_id`, `fourth_tendency_id`)
    VALUES ('원딜보다 딜 더 넣으려 하는 파이크', '허깅이나 한타 같은 안정적인 플레이를 좋아하는 당신!\n 챔프 폭이 넓고, 상향된 챔피언은 그때 그때 즐겨야 하는 성격입니다.\n\n후반 한타에서는 더욱 집중하고,\n결국 본인의 캐리로 게임을 승리로 이끄는 성향이군요!', 'Pyke', 'stable', 'mz', 'lately', 'killer');

-- Tier
INSERT INTO `tier`(`id`, `order_num`, `name`, `img`) VALUES ('unranked', 0, '언랭', 'unranked.png');
INSERT INTO `tier`(`id`, `order_num`, `name`, `img`) VALUES ('iron', 1, '아이언', 'iron.png');
INSERT INTO `tier`(`id`, `order_num`, `name`, `img`) VALUES ('bronze', 2, '브론즈', 'bronze.png');
INSERT INTO `tier`(`id`, `order_num`, `name`, `img`) VALUES ('silver', 3, '실버', 'silver.png');
INSERT INTO `tier`(`id`, `order_num`, `name`, `img`) VALUES ('gold', 4, '골드', 'gold.png');
INSERT INTO `tier`(`id`, `order_num`, `name`, `img`) VALUES ('platinum', 5, '플래티넘', 'platinum.png');
INSERT INTO `tier`(`id`, `order_num`, `name`, `img`) VALUES ('emerald', 6, '에메랄드', 'emerald.png');
INSERT INTO `tier`(`id`, `order_num`, `name`, `img`) VALUES ('diamond', 7, '다이아몬드', 'diamond.png');
INSERT INTO `tier`(`id`, `order_num`, `name`, `img`) VALUES ('master', 8, '마스터', 'master.png');
INSERT INTO `tier`(`id`, `order_num`, `name`, `img`) VALUES ('grandmaster', 9, '그랜드마스터', 'grandmaster.png');
INSERT INTO `tier`(`id`, `order_num`, `name`, `img`) VALUES ('challenger', 10, '챌린저', 'challenger.png');

-- 1번문항
INSERT INTO `lbti_question` (`first_paragraph`, `second_paragraph`)
VALUES ('나의 사소한 실수 하나로 게임이 져가고 있다...', '우리 팀원이 화가 나 채팅으로 나에게 욕을 한다!');
INSERT INTO `lbti_answer` (`paragraph`, `score`, `lbti_question_id`, `tendency_id`)
VALUES ('나만 잘못했냐? 키보드 워리어의 힘을 보여준다.', 2, 1, 'challenging');
INSERT INTO `lbti_answer` (`paragraph`, `score`, `lbti_question_id`, `tendency_id`)
VALUES ('욕을 한 팀원이 실수할 때까지 기다렸다가 잽싸게 물음표 핑을 찍는다.', 1, 1, 'challenging');
INSERT INTO `lbti_answer` (`paragraph`, `score`, `lbti_question_id`, `tendency_id`)
VALUES ('할많하않... 그냥 열쇠 벌이라고 생각하고 게임 종료 후 리폿한다.', 1, 1, 'stable');
INSERT INTO `lbti_answer` (`paragraph`, `score`, `lbti_question_id`, `tendency_id`)
VALUES ('내가 실수한 거니까ㅠㅠ 이 기회로 실수를 하지 않도록 더 집중한다.', 2, 1, 'stable');

-- 2번문항
INSERT INTO `lbti_question` (`first_paragraph`, `second_paragraph`)
VALUES ('나의 모스트가 이번 시즌에 너프를 먹어 큰 타격이 생겼다!', '다음 큐에 나는 무슨 챔피언을 픽할까?');
INSERT INTO `lbti_answer` (`paragraph`, `score`, `lbti_question_id`, `tendency_id`)
VALUES ('장인은 도구를 탓하지 않는다! 가장 자신있는 모스트 챔피언을 픽한다.', 2, 2, 'vintage');
INSERT INTO `lbti_answer` (`paragraph`, `score`, `lbti_question_id`, `tendency_id`)
VALUES ('계획적으로 이번 시즌의 모스트 챔피언 운영법을 공부하고, 가장 자신있는 챔피언을 픽한다.', 1, 2, 'vintage');
INSERT INTO `lbti_answer` (`paragraph`, `score`, `lbti_question_id`, `tendency_id`)
VALUES ('이 챔피언은 버프를 엄청 받았네! 이번 시즌에서 가장 강해보이는 챔피언을 픽한다.', 1, 2, 'mz');
INSERT INTO `lbti_answer` (`paragraph`, `score`, `lbti_question_id`, `tendency_id`)
VALUES ('이번 메타는 이 챔프지ㅋㅋ 최근 경기에서 제일 많이 보이는 챔피언을 픽한다.', 2, 2, 'mz');

-- 3번문항
INSERT INTO `lbti_question` (`first_paragraph`, `second_paragraph`)
VALUES ('나는 바텀에서 라인전을 하는 왕귀 듀오,', '그런데 상대 이즈리얼의 에임 상태가 조금 이상하다.');
INSERT INTO `lbti_answer` (`paragraph`, `score`, `lbti_question_id`, `tendency_id`)
VALUES ('잘 굴린 눈덩이가 천 리 간다. 라인전 킬각을 노린다!', 2, 3, 'early');
INSERT INTO `lbti_answer` (`paragraph`, `score`, `lbti_question_id`, `tendency_id`)
VALUES ('얄밉게 한 대씩 때려주며 정글에게 도움을 청한다.', 1, 3, 'early');
INSERT INTO `lbti_answer` (`paragraph`, `score`, `lbti_question_id`, `tendency_id`)
VALUES ('이따 나 크면 보자... 스킬을 피해주며 입맛을 다신다.', 1, 3, 'lately');
INSERT INTO `lbti_answer` (`paragraph`, `score`, `lbti_question_id`, `tendency_id`)
VALUES ('나는 큰 그림을 본다. 타워에 허깅한 채 미니언을 받아 먹어준다.', 2, 3, 'lately');

-- 4번문항
INSERT INTO `lbti_question` (`first_paragraph`, `second_paragraph`)
VALUES ('탑에서 타워를 밀던 중 미드에서 한타가 벌어졌다.', '내가 합류해야 할까?');
INSERT INTO `lbti_answer` (`paragraph`, `score`, `lbti_question_id`, `tendency_id`)
VALUES ('롤은 원래 포탑 미는 게임이다. 포탑을 한 라인이라도 더 밀어놓는 게 맞다!', 1, 4, 'detector');
INSERT INTO `lbti_answer` (`paragraph`, `score`, `lbti_question_id`, `tendency_id`)
VALUES ('롤은 함께 하는 게임이다. 당장 우리 팀원들과 한타를 하러 간다.', 1, 4, 'killer');

-- 5번문항
INSERT INTO `lbti_question` (`first_paragraph`, `second_paragraph`)
VALUES ('적의 쌍둥이 타워를 밀고 있을 때', '적팀의 샤코가 시야에 보이지 않는다!');
INSERT INTO `lbti_answer` (`paragraph`, `score`, `lbti_question_id`, `tendency_id`)
VALUES ('우리 집 부셔지는 거 아니야? 바로 귀환.', 1, 5, 'challenging');
INSERT INTO `lbti_answer` (`paragraph`, `score`, `lbti_question_id`, `tendency_id`)
VALUES ('너네 집 먼저 부술 거야! 더 힘차게 적팀 쌍둥이 타워를 민다.', 1, 5, 'stable');

-- 6번문항
INSERT INTO `lbti_question` (`first_paragraph`, `second_paragraph`)
VALUES ('새롭게 시작된 이번 시즌~', '당신은 패치노트를?');
INSERT INTO `lbti_answer` (`paragraph`, `score`, `lbti_question_id`, `tendency_id`)
VALUES ('남이 알려주지 않으면 패치 소식을 모른다...', 2, 6, 'vintage');
INSERT INTO `lbti_answer` (`paragraph`, `score`, `lbti_question_id`, `tendency_id`)
VALUES ('대규모 패치가 되었을 때 큰 변경사항만 확인한다.', 1, 6, 'vintage');
INSERT INTO `lbti_answer` (`paragraph`, `score`, `lbti_question_id`, `tendency_id`)
VALUES ('패치했다는 소식을 접했을 때 챙겨본다.', 1, 6, 'mz');
INSERT INTO `lbti_answer` (`paragraph`, `score`, `lbti_question_id`, `tendency_id`)
VALUES ('매 업데이트마다 꼼꼼히 챙겨본다.', 2, 6, 'mz');


-- 7번문항
INSERT INTO `lbti_question` (`first_paragraph`, `second_paragraph`)
VALUES ('우리 팀 서포터는 블리츠크 랭크.', '탑 포지션인 나는 인베를 가야할까?');
INSERT INTO `lbti_answer` (`paragraph`, `score`, `lbti_question_id`, `tendency_id`)
VALUES ('인베로 힘의 차이를 보여준다. 바로 함께 인베를 간다!', 2, 7, 'early');
INSERT INTO `lbti_answer` (`paragraph`, `score`, `lbti_question_id`, `tendency_id`)
VALUES ('한 입만 즐길 수도 있으니까. 팀원이 인베 핑을 찍으면 합류한다.', 1, 7, 'early');
INSERT INTO `lbti_answer` (`paragraph`, `score`, `lbti_question_id`, `tendency_id`)
VALUES ('아래는 거리가 너무 멀어 미드 근처를 돌아다니다가 탑으로 간다.', 1, 7, 'lately');
INSERT INTO `lbti_answer` (`paragraph`, `score`, `lbti_question_id`, `tendency_id`)
VALUES ('한 번 탑은 영원한 탑. 다른 곳 따위 가지 않는다.', 2, 7, 'lately');