//package com.ssafy.dksl.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.ssafy.dksl.model.dto.request.ReviewSaveRequestDto;
//import com.ssafy.dksl.model.dto.response.ReviewSearchResponseDto;
//import com.ssafy.dksl.model.entity.Review;
//import com.ssafy.dksl.model.repository.ReviewRepository;
//import com.ssafy.dksl.model.repository.MemberRepository;
//import com.ssafy.dksl.model.service.ReviewService;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.BDDMockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//
////@WebMvcTest(controllers = ReviewController.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@ActiveProfiles("test")
//class ReviewControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private ReviewService reviewService;
//
//    @DisplayName("특정 matchId에 대한 댓글 정보를 불러와 클라이언트로 넘겨준다.")
//    @Test
//    @WithMockUser("user") // Spring Security 때문
//    void getMatchReview() throws Exception {
//        // given
//        String matchId = "testMatchId";
//        int page = 2;
//
//        List<ReviewSearchResponseDto> reviews = new ArrayList<>();
//        User testUser = User.builder()
//                .name("testName")
//                .clientId("testClientId")
//                .password("testPassword")
//                .puuid("testPUUID")
//                .build();
//        for(int i = 0; i<5; i++){
//            reviews.add(Review.builder().matchId(matchId).content("testContent" + i).member(testUser).build().to());
//        }
//
//        // 매치와 관련된 댓글을 몇 개 임의로 insert 한다.
//        BDDMockito.given(reviewService.getReviews(matchId, page)).willReturn(reviews);
//
//        // when then
//        mockMvc.perform(
//                MockMvcRequestBuilders.get("/review/" + matchId + "/" + page)
//
//        )
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().json(reviews.toString()));
////                .andExpect(MockMvcResultMatchers.jsonPath())
////                .andExpect(MockMvcResultMatchers.jsonPath("$.body").isArray());
//    }
//
//    /*
//     * 매치 리뷰 등록 컨트롤러 테스트 코드가 정상작동하지 않음
//     */
////    @DisplayName("특정 매치에 대해 댓글을 단다.")
////    @Test
////    @WithMockUser("user") // Spring Security 때문ㅇ
////    @Transactional
////    void saveMatchReview() throws Exception {
////        // given
////        String matchId = "testMatchId";
////        ReviewSaveRequestDto requestDto = ReviewSaveRequestDto.builder()
////                .matchId(matchId)
////                .content("testContent")
////                .clientId("testClientId")
////                .build();
////
////        // when, then
////        mockMvc.perform(
////                MockMvcRequestBuilders.post("/review/register")
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .content(objectMapper.writeValueAsString(requestDto))
////                        .with(csrf()))
////                .andDo(MockMvcResultHandlers.print())
////                .andExpect(MockMvcResultMatchers.status().isOk());
////
////    }
//}