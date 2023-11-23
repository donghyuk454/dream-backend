package com.dream.application.common.util.batch.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ItemBufferTest {

    private ItemBuffer<String> itemBuffer;

    @BeforeEach
    public void beforeEach(){
        itemBuffer = new ItemBuffer<>();
    }

    @Test
    @DisplayName("add 함수를 통해 item 을 추가합니다.")
    void addItem() {
        //given
        String item1 = "item1";
        String item2 = "item2";

        //when
        itemBuffer.add(item1);
        itemBuffer.add(item2);

        //then
        assertThat(itemBuffer.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("addAll 함수를 통해 item 에 List 내용을 추가할 수 있습니다.")
    void addAllItem() {
        //given
        List<String> items = new ArrayList<>();
        items.add("item1");
        items.add("item2");

        //when
        itemBuffer.addAll(items);

        //then
        assertThat(itemBuffer.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("poll 함수를 통해 첫번째 item 을 꺼낼 수 있습니다.")
    void pollItem() {
        //given
        addThreeItems();

        //when
        String item = itemBuffer.poll();

        //then
        assertThat(itemBuffer.size()).isEqualTo(2);
        assertThat(item).isEqualTo("item1");
    }

    @Test
    @DisplayName("buffer 가 비어 있는 경우, isEmpty 함수는 True 를 반환합니다.")
    void isEmptyTrue_WhenBufferIsEmpty() {
        //given
        //when
        Boolean result = itemBuffer.isEmpty();

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("buffer 가 비어 있지 않은 경우, isEmpty 함수는 False 를 반환합니다.")
    void isEmptyFalse_WhenBufferIsNotEmpty() {
        //given
        addOneItem();

        //when
        Boolean result = itemBuffer.isEmpty();

        //then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("buffer 가 비어진 경우, isEmpty 함수는 True 를 반환합니다.")
    void isEmptyTrue_WhenBufferBeEmpty() {
        //given
        addOneItem();
        itemBuffer.poll();

        //when
        Boolean result = itemBuffer.isEmpty();

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("buffer 비어 있는 경우, size 함수는 0을 반환합니다.")
    void emptyBufferSize() {
        //given
        //when
        Integer result = itemBuffer.size();

        //then
        assertThat(result).isZero();
    }

    @Test
    @DisplayName("buffer 비어 있지 않은 경우, size 함수는 들어 있는 item 갯수를 반환합니다.")
    void notEmptyBufferSize() {
        //given
        addThreeItems();

        //when
        Integer result = itemBuffer.size();

        //then
        assertThat(result).isEqualTo(3);
    }

    private void addOneItem() {
        String item = "item";
        itemBuffer.add(item);
    }

    private void addThreeItems() {
        List<String> items = new ArrayList<>();
        items.add("item1");
        items.add("item2");
        items.add("item3");
        itemBuffer.addAll(items);
    }
}