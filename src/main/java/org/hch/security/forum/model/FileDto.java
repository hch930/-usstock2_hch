package org.hch.security.forum.model;

import groovy.transform.ToString;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FileDto {
	private Long id;
    private String oringalFilename;
    private String filename;
    private String filePath;
    
    public File toEntity() {
        File build = File.builder()
                .id(id)
                .oringalFilename(oringalFilename)
                .filename(filename)
                .filePath(filePath)
                .build();
        return build;
    }

    @Builder
    public FileDto(Long id, String oringalFilename, String filename, String filePath) {
        this.id = id;
        this.oringalFilename = oringalFilename;
        this.filename = filename;
        this.filePath = filePath;
    }
}
