※ 1일차 ※
기본세팅완료
------------------------------------------------------------------------------------------------------------------------------------------------------------
※ 2일차 ※

컴포지션 공부하기.
리플렉션 공부하기.

엔티티에는 가급적 Setter를 사용하지 말자.
모든 연관관계는 지연로딩으로 설정해야 한다.(LAZY로 설정하기.) EAGER로는 X.

컬렉션은 엔티티를 영속화 할 때 하이버네이트가 컬렉션을 본인이 추적할 수 있는 형태로 바꾸기 때문에
컬렉션은 필드에서 초기화 해두고 그것을 바꾸지말고 그대로 쓰는게 좋다. (new ArrayList()<>)

@OneToMany(cascade = CascadeType.ALL) 옵션을 사용하면
자신을 persist할때 해당 컬럼에 포함된 테이블도 persist 안해도 저장됨.