# ROOMUS 기획
> 서비스 개요
  ROOMUS는 사용자의 실제 집에 가구를 metavers 공간안에서 직접 배치하며 인테리어를 직접 해보고 인테리어에서 사용되었던 제품을 직접 구매할 수 있는 플랫폼 입니다.

> 시장 규모
  한국건설산업연구원의 통계 자료의 따르면 국내 인테리어・리모델링 시장은 올해(2022년) 60조원에 이를 전망으로 보고 있다.
  이는 지난해 41조5000억원보다 44.5%나 증가한 수치이며 앞으로 점차 인테리어 시장은 성장할 것으로 보고있다.
  
> 기획의도
  위와 같이 인테리어 산업이 빠르게 성장함에 따라 여러가지 방식으로 인터리어를 보다 편리하게 지원하는 방식의 서비스가 제공되고 있다.
  그러나 기술적인 제약으로 사용자의 집 구조를 따라할 수 있으나 기존의 가구에 새로운 가구를 더하는 것에 어려움이 존재하여 
  리모델링 혹은 신혼집과 같이 전체 가구를 새롭게 배치해야 하는 경우에 사용성이 높아진다는 특징과 함께 사생활 노출을 즐기는 현대인과 거리가 있다.
  이러한 점을 고안하여 ROOMUS는 사용자의 집안 사진을 업로드 하게 되면 사진속 가구를 인식하여 비슷한 제품을 추천하여 보다 쉽게 익순한 공간을 만들 수 있도록 서비스를 제공하며
  사용자가 만든 공간을 사용자의 선택에 따라 공개 여부를 결정 할 수 있도록 서비스 하여 사용자 접근성을 높이고자 기획하게 되었다.
  위 서비스를 통해 구현된 방의 인테리어 소품은 가상 공간에서 주문을 할 수 있으며 친구들과 함께 공간을 인테리어 하는 방식의 서비스도 지원하고 있다.
## 프로젝트 목표
1. 협업을 잘할 수 있는 역량을 기를 수 있도록 github의 다양한 기능을 사용해보기.
2. 구현만을 위한 프로젝트가 아닌 유지보수, 잘 알아 볼 수 있는 코드 작성하기
  - 도메인 주도 개발 (이벤트 스토밍 사용하여 프로젝트 설계하기)
  - 조회에 사용되는 트래픽을 분산하기 위해서 CUD(Command)와 R(Query)을 분리하기. (CQRS) 

## 인프라 아키텍처
![Room Us ( 융합 프로젝트 )](https://user-images.githubusercontent.com/96860725/204202581-ffce69a4-e795-407e-aa06-e9fb73884ce6.png)

## 주요 기능 시퀀스 다이어 그램
상품(가구) 업로드 및 사진 검색
![가구 업로드 시퀀스](https://user-images.githubusercontent.com/96860725/204202213-7a2017ea-d200-4024-a755-d4b66668cfe6.png)

## 사용 기술 스택
![사용 기술 스텍](https://user-images.githubusercontent.com/67566068/204131442-d6a865c2-9835-4780-874e-ddf67043281c.png)

## Branch 관리  
전반적인 틀은 git flow 브랜치 전략을 사용했습니다. 프로젝트를 관리하는 인원이 적음에 따라 release 브랜치는 필요하지 않을 것으로 판단하여 팀원들과의 회의 후에 아래와 같이 4개의 브랜치만 사용하는 것으로 결정하였습니다.  
![브랜치 관리](https://user-images.githubusercontent.com/96860725/204175676-9cf7e3a6-c63e-4204-b202-c3b0f2ab7a2b.png)
- main: 실제 prod배포를 위한 main 브랜치
- hotfix: main 브랜치에서 발생하는 오류를 긴급하게 수정해야 하는 경우 사용
- develop: 개발 서버로 prod서버와 영향을 주지 않으면서 개발하기 위해 사용
- feature: develop을 개발하면서 기능단위로 develop서버에 영향을 주지 않기 위해서 사용.

## CI/CD
생산성을 향상하기 위해서 Jenkin를 사용해서 CI/CD 파이프라인을 구축하였습니다.
또한 Jenkins의 관리 페이지를 통해서 빌드 성공여부(실패시 실패사유)를 확인할 수 있도록 하였습니다.
![CI/CD](https://user-images.githubusercontent.com/96860725/204204214-5b7bc5e6-14c8-475b-9c7a-1abe215e9ac6.png)
![Jenkins 빌드 기록](https://user-images.githubusercontent.com/96860725/204204558-22a06548-8343-4413-a981-4a1543bfdd68.png)

## Issue 관리  
협업을 위해 팀원들과 Issue로 할일을 관리하였습니다. 
Issue 확인하러 가기 ->  
[미완료 Issue](https://github.com/mtvs-6man/roomus-application-server/issues)  
[완료된 Issue](https://github.com/mtvs-6man/roomus-application-server/issues?q=is%3Aissue+is%3Aclosed)

## Wiki 관리  
프로젝트를 진행하면서 서로 알면 좋은것들, 협업하기 위해 맞춰야하는 컨벤션 등을 Wiki를 통해서 팀원들이 쉽게 접근할 수 있도록 하였습니다.
Wiki 확인하러 가기 ->
[wiki 바로가기](https://github.com/mtvs-6man/roomus-application-server/wiki)

