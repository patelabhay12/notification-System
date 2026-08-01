package com.notification.api.models.response;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper= true)
@Data
public class FilterTemplateResponse extends BaseTemplateResponse<TemplateResponseDTO,Long>{


    public FilterTemplateResponse(List<TemplateResponseDTO> templateResponseDTOList, final boolean hasMoreElement, final Long totalCount){
        setData(templateResponseDTOList);
        setHasMoreElement(hasMoreElement);
        setTotalCount(totalCount);
    }
}
