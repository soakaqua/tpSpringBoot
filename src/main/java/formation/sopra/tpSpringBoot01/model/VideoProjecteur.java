package formation.sopra.tpSpringBoot01.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "video_projecteur")
public class VideoProjecteur extends Materiel {


		private String resolution;

		public VideoProjecteur() {
		}

		public String getResolution() {
			return resolution;
		}

		public void setResolution(String resolution) {
			this.resolution = resolution;
		}
		
}
