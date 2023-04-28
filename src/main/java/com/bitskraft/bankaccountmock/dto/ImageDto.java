package com.bitskraft.bankaccountmock.dto;

import lombok.Data;

import java.util.List;

@Data

public class ImageDto {
    private List<String> imageName;
    private List<String> encodedImage;
    private List<String> imagePath;

}
