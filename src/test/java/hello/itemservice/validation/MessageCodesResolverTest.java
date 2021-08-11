package hello.itemservice.validation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.FieldError;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.ObjectError;

import static org.assertj.core.api.Assertions.*;

public class MessageCodesResolverTest {

    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    void messageCodesResolverObject() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item");
        for (String messageCode : messageCodes) {
            System.out.println("messageCode = " + messageCode);
        }

        //여기서 나오는 messageCodes 값을 rejectValue()가 아래 에러 객체에 파라미터로 전달해서 생성한다.
//        new ObjectError("item", new String[]{"required.item", "requried"});
        assertThat(messageCodes).containsExactly("required.item", "required");
    }

    
/*
결과(4개 메시지 생성)
messageCode = required.item.itemName
messageCode = required.itemName
messageCode = required.java.lang.String
messageCode = required
*/
    @Test
    void messageCodesResolverField() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item", "itemName", String.class);
        for (String messageCode : messageCodes) {
            System.out.println("messageCode = " + messageCode);
        }

        assertThat(messageCodes).containsExactly("required.item.itemName", "required.itemName", "required.java.lang.String", "required");
//        bindingResult가 파라미터 정보를 사용해서 FieldError를 생성함
//        bindingResult.rejectValue("itemName", "required");
//        new FieldError("item", "itemName", null, false, messageCodes, null, null)
    }
}
