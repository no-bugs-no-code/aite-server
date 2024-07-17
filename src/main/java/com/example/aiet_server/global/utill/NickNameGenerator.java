package com.example.aiet_server.global.utill;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class NickNameGenerator {
    public NickNameGenerator() {
        first = new String[]{"느리게", "빠르게", "따뜻하게", "귀엽게", "멋있게", "예쁘게", "신기하게", "부드럽게", "힘차게", "재미있게"}; //10
        second = new String[]{"밥먹는", "웃는", "바라보는", "기뻐하는", "환영하는", "미소짓는", "응원하는", "몸흔드는", "걸어가는", "날아가는", "해엄치는", "뛰어가는", "기어가는", "잠자는", "일어나는", "앉는", "움직이는"}; //17
        third = new String[]{"고양이", "강아지", "참새", "물고기", "쥐", "소", "토끼", "오리", "병아리", "거북이", "비둘기", "말", "다람쥐", "호랑이"};//14
    }

    private final String[] first;
    private final String[] second;
    private final String[] third;
    public String generate() {
        Random random = new Random();
        String f = first[random.nextInt(first.length)];
        String s = second[random.nextInt(second.length)];
        String t = third[random.nextInt(third.length)];
        return f + s + t;
    }
}