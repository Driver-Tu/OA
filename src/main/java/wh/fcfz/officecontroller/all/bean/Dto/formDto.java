package wh.fcfz.officecontroller.all.bean.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;


/**
 * {
 *     formId:1,
 *     templateId:1,
 *     fieldAndValue:{
 *         1:"张三",
 *         2:"李四"
 *     }
 * }
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class formDto {
    private Integer formId;
    private Integer templateId;
    private Map<Integer, Object> fieldAndValue;
}
