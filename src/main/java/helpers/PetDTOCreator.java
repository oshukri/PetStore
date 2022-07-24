package helpers;

import datatransferobject.CategoryDTO;
import datatransferobject.PetDTO;
import datatransferobject.TagDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PetDTOCreator
{
    public PetDTO createFakePetDTO(){
        CategoryDTO category = CategoryDTO.builder().id(Math.abs(new Random().nextLong())).name("Dogs").build();
        List<String> photoUrls = new ArrayList<>();
        photoUrls.add("fakeUrl");
        List<TagDTO> tags = createFakeTagsDTO();
        return PetDTO.builder()
            .id(Math.abs(new Random().nextLong()))
            .name("doggo")
            .category(category)
            .photoUrls(photoUrls)
            .tags(tags)
            .status("available")
            .build();
    }

    private List<TagDTO> createFakeTagsDTO(){
        TagDTO tag = TagDTO.builder().id(Math.abs(new Random().nextLong())).name("doggo").build();
        List<TagDTO> tags = new ArrayList<>();
        tags.add(tag);
        return tags;
    }

    private List<TagDTO> createTagDTO(long id, String name){
        TagDTO tag = TagDTO.builder().id(id).name(name).build();
        List<TagDTO> tags = new ArrayList<>();
        tags.add(tag);
        return tags;
    }
}
