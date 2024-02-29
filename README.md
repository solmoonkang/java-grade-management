# 스프링을 이용해서 학생 성적 관리 구현하기 🧑🏻‍🎓

공통 API 응답 모델과 에러 모델을 만들고, 간단한 성적 저장 및 조회 기능 API 를 자바로 구현합니다.

## 📊 API 요구사항

---
1. **이름과 성적을 입력받아 저장하는 API**
    - 성적의 입력은 특정 값(위의 에러 응답일 경우, 학년이 6 이상)이 넘었을 경우에는 에러 응답이 출력됩니다.
2. **입력된 성적을 조회하는 API**
    - 성적 오름차순으로 조회가 되어야 합니다.
3. **특정 성적을 입력받아 해당 성적의 학생만 조회하는 API**

## 🎮 구현 요구사항

---
1. Controller 에서 응답 모델로 만들어 주어야 한다.

```java
@ResponseBody
public ApiResponse<Student> searchAllStudent() {
		Student result = studentService.getAll();
		return makeResponse(result);
}
```

- **ApiResponse<T>**:
    - 여러가지 데이터 타입을 results 로 넣을 수 있도록 제네릭을 사용하여 구현해야 한다.
- **makeResponse(T result), makeResponse(List<T> results)**:
    - 결과를 응답 객체로 만들어주기 위한 메소드이다.
    - 단건과 복수건에 대한 결과 모두 응답 객체로 만들어 줄 수 있도록 두 개 모두 구현해야 한다.

<br>

2. 에러 응답을 만들기 위해서는 **@ExceptionHandler를 사용하여 Exception의 데이터를 이용**해야 한다.

```java
@ExceptionHandler(CustomException.class)
@ResponseBody
public ApiResponse customExceptionHandler(HttpServletResponse response, CustomException e) {
		response.setStatus(HTTP 응답 값);
		return new ApiResponse(code, message, data);
}
```

<br>

3. **ExceptionHandler**에서 응답 모델을 만들 때 필요한 데이터가 포함될 수 있는 **CustomException을 구현**해야 한다.

```java
throw new CustomException(ErrorCode.SERVER_ERROR, "grade는 6 이상을 입력할 수 없습니다.", new InputRestriction(maxGrade));
```

- *EX. CustomException(ErrorCode, message, data)에서 ErrorCode는 enum으로 정의한다.*


## ⚠️ 응답 / 에러 모델 만들기

---
공통 API 응답 모델과 에러 모델을 만들고, 간단한 성적 저장 및 조회 기능 API를 자바로 구현합니다.

<br>
응답 모델의 예시는 다음과 같습니다.

```java
{
    "status": {
        "code": 2000,
        "message": "OK"
    },
    "metadata": {
        "resultCount": 2
    },
    "result": [
        {
            "name": "kim",
            "grade": "1"
        }, 
        {
            "name": "lee",
            "grade": "3"
        }
    ]
}
```

---
<br>

에러 모델의 예시는 다음과 같습니다.

```java
{
    "status": {
        "code": 5000,
        "message": "grade는 6 이상을 입력할 수 없습니다."
    },
    "data": {
        "inputRestriction": {
            "maxGrade": "6"
        }
    }
}
```


---
<br>

- **Status.code**: HTTP Status가 아닌 서버에서 정의하는 code 값이 담겨져 있어야 한다.
  - 정상 응답에서는 항상 200이다.

- **Status.message**: 정상 응답 시에는 “OK”, 에러 응답 시에는 에러에 대한 상세 이유를 담아준다.

- **Metadata.resultCount**: 정상 응답 시에 나타나는 값이다.
  - Results List의 count를 담아준다.

- **Results**: 정상 응답 시에 나타나는 값이다. 항상 List 형태로, 실제 응답으로 내주고 싶은 정보가 표시된다.

- **Data**: 에러 응답 시에 나타나는 값으로, 에러 응답 시 frontend에서 사용자에게 어떤 이유로 요청이 거부되었는지 명시하는 메시지를 담아준다.


## 📑 하위 과제

---
- 에러 응답에서 다음의 경우에서도 정상적으로 data가 응답의 결과로 나올 수 있도록 구현한다.

```java
public void method() {
    try {
        inner();
    } catch (Exception e) {
        throw new CustomException(ErrorCode.SERVER_ERROR, "grade는 6 이상을 입력할 수 없습니다.", null);
    }
}
		
public void inner() {
    throw new CustomException(
        ErrorCode.SERVER_ERROR, "grade는 6 이상을 입력할 수 없습니다.", new InputRestriction(maxGrade));
}
```

- 구현한 API 이외에 필요하다고 생각하는 API를 추가 구현하고, 위 API 요구사항에서 문제점이 발견될 경우 개선하도록 한다.

## 📂 결과물

---
