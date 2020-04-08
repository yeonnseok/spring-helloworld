# ✨실습 환경 구축하기✨
- 원활한 실습을 위해 수업 전에 실습 환경을 준비해 주세요.
- 환경 구축 간 어려움을 겪는다면 `#2기-레벨2질문방`을 활용해주세요.(자문 자답도 좋습니다. <span class=highlight>내가 모르는건 다른 크루들도 몰라요</span>)

### 0. Clone 받기
- [https://github.com/woowacourse/spring-helloworld#user-content-실습-환경-구축하기](https://github.com/woowacourse/spring-helloworld#user-content-실습-환경-구축하기)를 참고하여 clone 받고 정상 동작 확인

### 1. 현재 브랜치를 확인
- 아래 명령을 통해 현재 브랜치를 확인하기
> git branch --show-current

```
ryuseonghyeon@Brownui-MacBookPro-15 spring-helloworld % git branch --show-current
mvc

```

### 2. core 브랜치로 변경
- 아래 명령을 통해 core 브랜치로 변경하기
> git checkout core

```
ryuseonghyeon@Brownui-MacBookPro-15 spring-helloworld % git branch --show-current
mvc
ryuseonghyeon@Brownui-MacBookPro-15 spring-helloworld % git checkout core
Switched to branch 'core'
Your branch is up to date with 'origin/core'.
ryuseonghyeon@Brownui-MacBookPro-15 spring-helloworld % git branch --show-current
core
ryuseonghyeon@Brownui-MacBookPro-15 spring-helloworld % 

```

### 3. core 패키지 내부에 있는 테스트를 성공시키기
https://github.com/woowacourse/spring-helloworld/tree/core/src/test/java/wooteco/helloworld/core
