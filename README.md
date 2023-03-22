# Blog Search Based Service(BSBS) by keyword
[![Build Status](https://travis-ci.org/dwyl/esta.svg?branch=master)](https://travis-ci.org/)

키워드 기반의 블로그 검색 서비스입니다. 블로그를 키워드 검색으로 제공하는 API 를 통해 데이터를
정제하고, 실시간 키워드 랭킹 연산을 통해 목록 제공 및 실시간 검색 키워드 랭킹을 제공합니다.

* 제공 API
    * 키워드 블로그 검색
    * 실시간 검색 키워드 Top 10

## Usage
* 프로젝트 실행 방법1
    * 코드를 다운로드 받아 프로젝트 폴더에서 아래 명령을 수행하여 서버를 동작시킵니다.
```sh
$ git clone https://github.com/qnqnckck/BlogSearchBasedService.git
$ cd BlogSearchBasedService
$ ./gradlew clean bootRun
```
* 프로젝트 실행 방법2
  * [jar 다운로드](https://drive.google.com/file/d/1oFIwunouO7kyLee1a125q1R5bzB3832h/view?usp=share_link) 후 아래와 같이 실행합니다.

```sh
$ java -jar bsbs-0.0.1-SNAPSHOT.jar
```


* API 호출
    * Intellij에서 api.http 파일내 API를 호출
    * Swagger 접속
        * http://localhost:8080/swagger-ui.html 접속 후 API 규격 확인 및 호출


* 테스트 방법
    * 실시간 검색어 랭킹 전 키워드 블로그 검색 API를 호출
    * 동일 키워드로 블로그 검색 API를 호출
    * 실시간 검색 키워드 Top 10 API를 호출

## Description
해당 프로젝트를 진행하면서 고민했던 포인트들을 함께 설명합니다.
### 전체 시스템 Architecture
기능을 구현할 서버(API Server) 이외에 전체 아키텍처를 고민하여 개발 범위를 산정하였습니다.
* 1안
    * ![architecture1](https://user-images.githubusercontent.com/10949665/226908854-c6bf8581-51d8-47b5-b4dd-47a11e490add.png)
        * API Server에 대한 키워드 요청에 대한 로그 수집을 ELK를 활용합니다.
        * 검색어 랭킹의 경우 실시간으로 데이터의 정확성이 보장 될 필요가 없으므로 기반시스템 구성에 Jenkins 배치를 통해 주기적으로 Redis에 업데이트하여 랭킹을 제공합니다.


* 2안
    * ![architecture2](https://user-images.githubusercontent.com/10949665/226908864-55d22185-542b-42a1-8ed2-276aebaea010.png)
        * 실시간 랭킹을 제공하기 위한 방법으로 Redis Sorted Set(Zset) 기능을 사용합니다.
        * 요청이 올때마다 Redis에 업데이트하여 랭킹을 산정합니다.

최종적으로 2안으로 채택하였습니다. API 제공 서버 기준으로 실시간 랭킹 구현이 Redis에 api 서버에서 직업 업데이트하는 것으로 개발하였습니다.

### 설계 및 구현시 고려사항
* 응답성 개선
  * 키워드 검색시 성능 및 동시성 이슈를 해결하기 위해 싱글 스레드 기반의 전체 시스템이 공유하는 Global Redis Server가 있다는 가정하에 설계 하였습니다.
    * 로컬 동작시 테스트를 위해 임베디드 Redis 서버도 추가 구성하였습니다.
* 장애 처리 방안
  * 블로그 검색시 카카오 API의 Fallback이 발생하는 경우
    * 네이버 오픈 API를 통해 대체
    * 네이버 오픈 API 호출도 문제가 발생하는 경우 Empty List를 Default로 전달
* API 기능 확정을 고려한 설계
    * 검색어 기반의 경우 Content(검색 대상) 파라미터를 추가하여 추후 검색 API 신규 추가 없이 개발 가능

## IMPLEMENTATION
### 기술스택
* springboot
* cache : redis client
* assertj : unit test를 위한 라이브러리
* embedded redis : local profile에만 개발을 위해 반영
* swagger : apidoc 자동화 라이브러리

### 개발환경
* JDK 11
* IntelliJ 22.04.03
* gradle
* SpringBoot 3.0.4

### 기본 속성
* 서비스 포트
    * port : 8080
* 임베디드 Redis
    * server : localhost
    * port : 6379
* spring.profiles.active
    * default : local (개발 환경 기준으로 초기 설정)

