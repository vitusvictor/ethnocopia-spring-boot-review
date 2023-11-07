package com.ethnocopia.model;

import com.ethnocopia.dto.Response;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Constants {
    public static Response successResponse = new Response("00", "Success");
    public static Response notFound = new Response("99", "Not found");
}
