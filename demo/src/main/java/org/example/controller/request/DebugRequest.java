package org.example.controller.request;

import lombok.Data;

/**
 * @author duoyian
 * @date 2026/6/25
 */
@Data
public class DebugRequest implements java.io.Serializable {
    private static final long serialVersionUID = 4004264531004214354L;

    private String name;

    private String age;
}
