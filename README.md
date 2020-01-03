# Warming

## 기능

### 갤러리tab1
*인스타 글과 그림 show
*유튜브 영상 show
*관련 영상

### tab2
*카테고리 tab으로 구분(작성시 각 카테고리에 작성됨)
*본인들의 힘든 이야기 공유(직업과 나이대 별로 분류)
*응원문구 공유 및 투표
*(- 자신의 소확행 찾아가기)

### tab3
*로그인하기(휴대전화 간편 로그인, api 문자인증)
*친절온도(
*마이페이지(작성글 , 좋아요목록(글,그림) 

### 보류
*(1:1 대화)
*본인의 객관화(감정진단 및 상황파악)
*푸쉬알람

/////////////////// DB 스키마 /////////////////////
Visual Content table

*id 

*contentType(img or video or voice)

*category(healing video, cheer up video, relax img)

*제목

*설명

*yotube video url = EFZT7qrWXOc&list=PLFRxKyTKpgV9VD-0E17CvNdKJvcnVkCXA&index=6

*이미지 bitMap

*관련영상 묶어둔 json
