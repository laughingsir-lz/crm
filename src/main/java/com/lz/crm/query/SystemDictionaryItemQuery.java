package com.lz.crm.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SystemDictionaryItemQuery extends QueryObject {
    private Long parentId = -1L;//目录id
}
