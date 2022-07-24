package datatransferobject;

import java.util.List;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetDTO
{
    private long id;
    private String name;
    private CategoryDTO category;
    private List<String> photoUrls;
    private List<TagDTO> tags;
    private String status;
}
