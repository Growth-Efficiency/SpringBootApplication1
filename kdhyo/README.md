# 실전! 스프링 부트와 JPA 활용1 - 웹 애플리케이션 개발

## DB TABLE 생성은?

- JPA 로 간단하게 스크립트를 뽑은 후 수정할 부분을 수정해서 ddl을 가지고 있는다.

## Entity 설계 시 주의점

- Entity 에서는 가급적 Setter를 사용하지 말자.
  - Setter가 모두 열려있으면, 변경 포인트가 너무 많아 유지보수가 어렵다.

- 모든 연관관계는 지연로딩으로 설정하자.
  - 즉시로딩(`EAGER`)은 예측이 어렵고, 어떤 SQL이 실행될지 추적하기 어렵다.
    특히 JPQL을 실행할 때 N + 1 문제가 자주 발생한다.
  - 실무에서 모든 연관관계는 지연로딩(`LAZY`)으로 설정해야 한다.
  - 연관된 엔티티를 함께 DB에서 조회해야 하면, fetch join 또는 엔티티 그래프 기능을 사용한다.
  - @XToOne(OneToOne, ManyToOne)관계는 기본이 즉시로딩이므로 직접 지연로딩으로 설정해야 한다.

- 컬렉션은 필드에서 초기화를 바로 하자.
  - 컬렉션은 필드에서 바로 초기화 하는 것이 안전하다.
  - `null` 문제에서 안전하다.
  - 하이버네이트는 엔티티를 영속화 할 때, 컬렉션을 감싸서 하이버네이트가 제공하는 컬렉션으로 변경한다.  
    만약 임의의 메서드에서 컬렉션을 잘못 생성하면 하이버네이트 내부 메커니즘에 문제가 발생할 수 있다.  
    따라서 필드레벨에서 생성하는 것이 가장 안전하고, 코드도 간결하다.

## @NoArgsConstructor(access = AccessLevel.PROTECTED)

```
protected Order() {
}
```

## Entity Mapping cascade

```
@OneToMany(mappedBy = "order", cascade = ALL)
```

- cascade 가 ALL인 경우 상위 Entity가 persist될 때 같이 persist 가 된다.

### cascade 주의 점
- Order만 OrderItem, Delicery를 사용하기 때문에 이런 방식을 한거고, 아닌 경우에는 안하는 게 좋다.  
  개념이 잘 안잡혔을 경우는 안 쓰는 게 좋고, 나중에 리팩토링을 하는 게 좋다.  
  라이프사이클을 잘 알고 사용하자!