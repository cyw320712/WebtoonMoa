# 2022 하계 SW 코칭 프로그램 - Spring framework

## 프로젝트 소개
여러 플랫폼에 흩어져 있는 웹툰들을 모아서 장르별로 구분해서 볼 수 있는 플랫폼을 제작하는 프로젝트입니다.

각 플랫폼들의 웹툰을 크롤링해서 업데이트하는 요일과 장르, 웹툰을 볼 수 있는 장소까지 한 번에 확인할 수 있는 **웹툰 플랫폼의 플랫폼**입니다.

## 기능 요구사항
### 웹툰 조회
- 그냥 최신 웹툰 조회 (오늘치)
- 검색어 + tag 로 조회 (기본 태그는 ALL, 선택시 ALL 태그 사라짐)
  - 검색 결과에서 tag 하나 누를때마다 실시간으로 반영되게 설계 가능?

### 배치 작업
- 정해진 시간에 각 사이트 크롤링해오기

## Maintainer
 * 최영우 ([@cyw320712](https://github.com/cyw320712))

## 진행 상황
| 과정                       | 기간          | 리뷰여부 | 완료 여부 |
|--------------------------|-------------|------|-------|
 | 도메인 + Repository 설계 및 구현 | 6/11 ~ 6/12 | O    |       |
 | Service + Test 구현        | 6/13 ~ 6/19 |      |       |
 | Controller 구현 및 테스트      | 6/20 ~ 7/2  |      |       |
 | 크롤러 구현 및 연동              | 7/4 ~ 7/10  |      |       |
 | 프로젝트 마무리 및 배포            | 7/11 ~ 7/14 |      |       |
 | 코칭 결과 발표회                | 7/15 |      |       |

## 실행 방법
1. 프로젝트 코드를 받습니다.
2. IDE에서 build.gradle을 지정 후 프로젝트로 열기를 선택합니다.
3. src/main/java/skku/swcoaching/SwcoachingApplication.java에 들어가 코드를 실행해 줍니다.
4. localhost:8080/hello 로 접속해 정상 작동을 확인합니다.
