package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
// OrderServiceImp이 DiscountPolicy라는 역할과 구현을 직접적으로 관여함
public class OrderServiceImp implements OrderService{

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); DIP 위반!!
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); DIP 위반!!

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy; // dependency를 해제할 수 있다! 그런데 구현부는??

    public OrderServiceImp(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
