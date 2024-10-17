package com.example.hhp_ecommerce.Application.facade;

import com.example.hhp_ecommerce.Application.service.MemberService;
import com.example.hhp_ecommerce.domain.Member;
import com.example.hhp_ecommerce.interfaces.dto.point.PointChargeDto;
import com.example.hhp_ecommerce.interfaces.dto.point.PointResponseDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

    @SpringBootTest
    class MemberFacadeTest {

        @Autowired
        private MemberFacade memberFacade;

        @Autowired
        private MemberService memberService;

        @BeforeEach
        void setUp() {
            // 테스트 전에 필요한 데이터 셋업
            Member member = new Member(1L,  1000); // 유저 id: 1, 이름: testuser, 초기 포인트: 1000
            memberService.pointSave(member);
        }

        @Test
        void testPointLookup() {
            // userId 1의 포인트 조회
            PointResponseDto response = memberFacade.pointLookup(1L);

            assertNotNull(response);
            assertEquals(1000, response.point());
        }

        @Test
        void testPointCharge() {
            // userId 1에게 500 포인트 충전
            PointChargeDto pointChargeDto = new PointChargeDto(1L, 500);
            PointResponseDto response = memberFacade.pointCharge(pointChargeDto);

            assertNotNull(response);
            assertEquals(1500, response.point()); // 1000 + 500 = 1500 포인트로 업데이트 되었는지 확인
        }

        @Test
        void testPointChargeWithInvalidUser() {
            // 존재하지 않는 userId에 대한 포인트 충전 시도
            PointChargeDto pointChargeDto = new PointChargeDto(999L, 500);

            assertThrows(RuntimeException.class, () -> {
                memberFacade.pointCharge(pointChargeDto);
            });
        }

        @Test
        void testPointLookupWithInvalidUser() {
            // 존재하지 않는 userId에 대한 포인트 조회 시도
            assertThrows(RuntimeException.class, () -> {
                memberFacade.pointLookup(999L);
            });
    }

        @Test
        void testConcurrentPointCharge() throws InterruptedException, ExecutionException {
            int numberOfThreads = 10; // 10개의 스레드로 동시 포인트 충전 요청을 시뮬레이션
            ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
            CountDownLatch latch = new CountDownLatch(numberOfThreads);

            List<Callable<PointResponseDto>> tasks = new ArrayList<>();

            // 각 스레드에서 100 포인트를 충전하도록 작업 생성
            for (int i = 0; i < numberOfThreads; i++) {
                tasks.add(() -> {
                    try {
                        latch.countDown();  // 모든 스레드가 준비될 때까지 대기
                        latch.await();
                        PointChargeDto pointChargeDto = new PointChargeDto(1L, 100); // 1L 유저에게 100 포인트 충전
                        return  memberFacade.pointCharge(pointChargeDto);

                    } catch (InterruptedException e) {
                        throw new IllegalStateException(e);
                    }
                });
            }

            // 모든 스레드가 충전 작업을 시작하도록 실행
            List<Future<PointResponseDto>> results = executorService.invokeAll(tasks);

            PointResponseDto pointResponseDto = memberFacade.pointLookup(1L);

            // 1000 + (100 * 10) = 2000 포인트가 되어야 함
            assertEquals(2000, pointResponseDto.point());

            // 테스트 후 스레드 종료
            executorService.shutdown();
        }
    }