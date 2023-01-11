package domain.utils.fixture;

import java.time.LocalDateTime;

import org.exemple.data.ETypeActivite;
import org.exemple.data.PoliceAssuranceDto;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class PoliceAssuranceTemplateLoader  implements TemplateLoader {

    @Override
    public void load() {

        Fixture.of(PoliceAssuranceDto.class).addTemplate("valid", new Rule(){{
            add("id", 1L);
            add("nom", "Abdel");
            add("statut", ETypeActivite.ACTIVITE);
            add("dateDebutCouverture", LocalDateTime.now());
            add("dateFinCouverture", LocalDateTime.now());
            add("dateCreation", LocalDateTime.now());
            add("dateMAJ", LocalDateTime.now());

        }});

        Fixture.of(PoliceAssuranceDto.class).addTemplate("valid_update", new Rule(){{
        	add("id", 1L);
            add("nom", "Abdel2");
            add("statut", ETypeActivite.ACTIVITE);
            add("dateDebutCouverture", LocalDateTime.now());
            add("dateFinCouverture", LocalDateTime.now());
            add("dateCreation", LocalDateTime.now());
            add("dateMAJ", LocalDateTime.now());
        }});

        Fixture.of(PoliceAssuranceDto.class).addTemplate("invalid", new Rule(){{
            add("id", null);
            add("name", null);
            add("dateOfBirth", null);
            add("cpf", null);
            add("email", null);
        }});
    }
}
