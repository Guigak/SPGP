# SPGP README

## 게임 소개
+ ### 게임 제목
  + #### 리듬 계단

+ ### 게임 컨셉
  + #### 무한의 계단에 리듬 요소를 추가하여 노래에 맞춰 계단을 올라가는 2D 리듬 게임
    ![image](https://github.com/Guigak/SPGP/assets/97238914/0d188971-abd3-41a0-96a3-818b551930e0)
    + 무한의 계단
 
    ![sample](https://github.com/Guigak/SPGP/assets/97238914/9f233cf9-381b-455a-b999-7fa62a5d61e6)
    + 리듬 요소를 추가한 리듬 계단


---


## 개발 일정에 따른 진행도
+ ### 1주차 ~ 2주차 (04.04 ~ 04.17)
  + #### 리소스 수집 및 제작
    + ##### 100%

+ ### 3주차 (04.11 ~ 04.17)
  + #### 시작 & 메인 화면
    + ##### 100%

+ ### 4주차 ~ 6주차 (04.25 ~ 05.15)
  + #### 플레이 화면 & 중간 점검
    + ##### 98% (2% : 게임 진행 게이지바)

+ ### 7주차 (04.11 ~ 04.17)
  + #### 채보 제작
    + ##### 75% (25% : Time Shift(HARD))

+ ### 8주차 (04.11 ~ 04.17)
  + #### 보충 구현 & 디버깅
    + ##### 98%

+ ### 9주차 (04.11 ~ 04.17)
  + #### 마무리
    + ##### 100%


---


## git commit 수
+ ### 1주차 (04.04 ~ 04.10)
  + #### 0 개

+ ### 2주차 (04.11 ~ 04.17)
  + #### 10 개

+ ### 3주차 (04.18 ~ 04.24)
  + #### 5 개

+ ### 4주차 (04.25 ~ 05.01)
  + #### 2 개

+ ### 5주차 (05.02 ~ 05.08)
  + #### 8 개

+ ### 6주차 (05.09 ~ 05.15)
  + #### 20 개

+ ### 7주차 (05.16 ~ 05.22)
  + #### 1 개

+ ### 8주차 (05.23 ~ 05.29)
  + #### 0 개

+ ### 9주차 (05.30 ~ 06.05)
  + #### 11 개

+ ### 9주차 이후 (06.05 ~)
  + #### 53 개

+ ### 표
|주차|커밋 수|그래프|
|:---:|:---:|:---:|
|1주차|0|![image](https://github.com/Guigak/SPGP/assets/97238914/19c07206-c78d-4e74-b61b-ac04e12b7f8b)|
|2주차|10|![image](https://github.com/Guigak/SPGP/assets/97238914/24272cbc-de4f-4299-90e6-5a825606f824)|
|3주차|5|![image](https://github.com/Guigak/SPGP/assets/97238914/32caf386-7cb0-43cf-90b4-8884d0ab9fbf)|
|4주차|2|![image](https://github.com/Guigak/SPGP/assets/97238914/53cb27f5-6a1e-4692-9fc5-459334e26de0)|
|5주차|8|![image](https://github.com/Guigak/SPGP/assets/97238914/691d7da6-2106-4433-9c69-c9da05ee82fb)|
|6주차|20|![image](https://github.com/Guigak/SPGP/assets/97238914/e753e8dc-0871-4993-9f4a-27eec600195a)|
|7주차|1|그래프 없음|
|8주차|0|![image](https://github.com/Guigak/SPGP/assets/97238914/f49ba336-4690-4229-93ae-652fc8ea8a2e)|
|9주차|11|![image](https://github.com/Guigak/SPGP/assets/97238914/efb12331-63ca-404f-9ba2-c458218a9049)|
|9주차 이후|53|![image](https://github.com/Guigak/SPGP/assets/97238914/38faf8e9-c063-4381-9366-8796e3136446)![image](https://github.com/Guigak/SPGP/assets/97238914/67eedc18-15b8-4dac-a8b8-cfc72a5bfd8f)|

## 변경된 목표
+ ### 게이지바 제거
  + #### 노래가 끝난 것처럼 속이는 패턴을 넣기 위해


---


## 사용된 기술
+ ### Scene Stack
  + #### 수업시간에 만든 Scene 클래스를 사용하여 Scene들을 Stack에 담아 관리
+ ### Camera Lagging
  + #### 카메라가 한 번에 이동하지 않고 서서히 이동

## 참고한 것들
+ ### Framework
  + #### Button
    + ##### 해당 클래스를 참고하여 Button 클래스를 만듦
  + #### Sprite
    + ##### 해당 클래스를 참고하여 HorizontalScrollableSprites, NumberSprites 등 비트맵을 활용하는 클래스를 만듦
  + #### HorzScrollBackground, VertScrollBackground
    + ##### 해당 클래스들을 참고하여 대각선으로 움직이는 DiagonalScrollBackground 클래스를 만듦

+ ### TapTu
  + #### Song
    + ##### 해당 클래스를 참고하여 계단을 읽어와 생성하는 StairManager 클래스를 만듦

## 수업내용에서 차용한 것
+ ### Framework
  + #### GameActivity
  + #### Sprite
  + #### BitmapPool
  + #### Sound
  + #### Scene
  + #### GameView
  + #### Metrics

## 직접 개발한 것
+ ### HorizontalScrollableSprites
  + #### 비트맵을 추가하고 좌우로 슬라이드하여 스크롤링 할 수 있는 클래스를 만듦
+ ### DiagonalScrollBackground
  + #### 대각선으로 움직이는 클래스를 만듦
+ ### Camera
  + #### 터치 이벤트에 따라 움직이며 화면에 출력하는 공간을 정하는 클래스를 만듦


---


## 하고 싶었지만 못 한 것들
+ ### 캐릭터 마다 애니메이션을 다르게 하려고 하였으나 하지 못 함
+ ### 일정 관리가 제대로 되지 않음
+ ### Time Shift(HARD)의 채보를 끝까지 제작하려 했으나 아이디어가 생각나지 않음

## 결국 해결하지 못한 문제/버그
+ ### 없음

## 기말 프로젝트를 하면서 겪은 어려움
+ ### 없음


---


## 수업에 대한 내용
+ ### 이번 수업에서 기대한 것
  + #### 스마트폰 게임 프로그래밍에 대한 기초적인 지식

+ ### 이번 수업에서 얻은 것
  + #### 스마트폰 게임 프로그래밍에 대한 기초적인 지식
  + #### 여러 디자인 패턴

+ ### 이번 수업에서 얻지 못한 것
  + #### 없음

+ ### 더 좋은 수업이 되기 위해 변화할 점
  + #### 없음


---
