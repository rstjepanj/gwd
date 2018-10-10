package hr.tvz.gwd.model;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DesignProperties implements Serializable {

	private static final long serialVersionUID = 6135855886511036537L;

	public static final int colorMin = 0;
	public static final int colorMax = 255;
	public static final int textSizeMin = 10;
	public static final int textSizeMax = 30;
	public static final int fontMin = 0;
	public static final int fontMax = 9;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private Integer ocjena;
	private Integer navbarBackColorR;
	private Integer navbarBackColorG;
	private Integer navbarBackColorB;
	private Integer navbarTextColorR;
	private Integer navbarTextColorG;
	private Integer navbarTextColorB;
	private Integer bodyBackColorR;
	private Integer bodyBackColorG;
	private Integer bodyBackColorB;
	private Integer bodyTextColorR;
	private Integer bodyTextColorG;
	private Integer bodyTextColorB;
	private Integer linkColorR;
	private Integer linkColorG;
	private Integer linkColorB;
	private Integer wellBackColorR;
	private Integer wellBackColorG;
	private Integer wellBackColorB;
	private Integer bodyTextSize;
	private Integer leadTextSize;
	private Integer h1TextSize;
	private Integer h2TextSize;
	private Integer h4TextSize;
	private Integer bodyFont;
	private Integer hPlusLeadFont;

	public DesignProperties() {

	}

	public DesignProperties(boolean generateRandom) {
		this.navbarBackColorR = ThreadLocalRandom.current().nextInt(colorMin, colorMax + 1);
		this.navbarBackColorG = ThreadLocalRandom.current().nextInt(colorMin, colorMax + 1);
		this.navbarBackColorB = ThreadLocalRandom.current().nextInt(colorMin, colorMax + 1);
		this.navbarTextColorR = ThreadLocalRandom.current().nextInt(colorMin, colorMax + 1);
		this.navbarTextColorG = ThreadLocalRandom.current().nextInt(colorMin, colorMax + 1);
		this.navbarTextColorB = ThreadLocalRandom.current().nextInt(colorMin, colorMax + 1);
		this.bodyBackColorR = ThreadLocalRandom.current().nextInt(colorMin, colorMax + 1);
		this.bodyBackColorG = ThreadLocalRandom.current().nextInt(colorMin, colorMax + 1);
		this.bodyBackColorB = ThreadLocalRandom.current().nextInt(colorMin, colorMax + 1);
		this.bodyTextColorR = ThreadLocalRandom.current().nextInt(colorMin, colorMax + 1);
		this.bodyTextColorG = ThreadLocalRandom.current().nextInt(colorMin, colorMax + 1);
		this.bodyTextColorB = ThreadLocalRandom.current().nextInt(colorMin, colorMax + 1);
		this.linkColorR = ThreadLocalRandom.current().nextInt(colorMin, colorMax + 1);
		this.linkColorG = ThreadLocalRandom.current().nextInt(colorMin, colorMax + 1);
		this.linkColorB = ThreadLocalRandom.current().nextInt(colorMin, colorMax + 1);
		this.wellBackColorR = ThreadLocalRandom.current().nextInt(colorMin, colorMax + 1);
		this.wellBackColorG = ThreadLocalRandom.current().nextInt(colorMin, colorMax + 1);
		this.wellBackColorB = ThreadLocalRandom.current().nextInt(colorMin, colorMax + 1);
		this.bodyTextSize = ThreadLocalRandom.current().nextInt(textSizeMin, textSizeMax + 1);
		this.leadTextSize = ThreadLocalRandom.current().nextInt(textSizeMin, textSizeMax + 1);
		this.h1TextSize = ThreadLocalRandom.current().nextInt(textSizeMin, textSizeMax + 1);
		this.h2TextSize = ThreadLocalRandom.current().nextInt(textSizeMin, textSizeMax + 1);
		this.h4TextSize = ThreadLocalRandom.current().nextInt(textSizeMin, textSizeMax + 1);
		this.bodyFont = ThreadLocalRandom.current().nextInt(fontMin, fontMax + 1);
		this.hPlusLeadFont = ThreadLocalRandom.current().nextInt(fontMin, fontMax + 1);
	}

	public double[] getNormalizedInputArray() {
		double[] output = {
				navbarBackColorR / (double) colorMax,
				navbarBackColorG / (double) colorMax,
				navbarBackColorB / (double) colorMax,
				navbarTextColorR / (double) colorMax,
				navbarTextColorG / (double) colorMax,
				navbarTextColorB / (double) colorMax,
				bodyBackColorR / (double) colorMax,
				bodyBackColorG / (double) colorMax,
				bodyBackColorB / (double) colorMax,
				bodyTextColorR / (double) colorMax,
				bodyTextColorG / (double) colorMax,
				bodyTextColorB / (double) colorMax,
				linkColorR / (double) colorMax,
				linkColorG / (double) colorMax,
				linkColorB / (double) colorMax,
				wellBackColorR / (double) colorMax,
				wellBackColorG / (double) colorMax,
				wellBackColorB / (double) colorMax,
				bodyTextSize / (double) textSizeMax,
				leadTextSize / (double) textSizeMax,
				h1TextSize / (double) textSizeMax,
				h2TextSize / (double) textSizeMax,
				h4TextSize / (double) textSizeMax,
				bodyFont / (double) fontMax,
				hPlusLeadFont / (double) fontMax
		};
		return output;
	}
	
	public double getNormalizedOcjena() {
		return ocjena / (double) 5;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getOcjena() {
		return ocjena;
	}

	public void setOcjena(Integer ocjena) {
		this.ocjena = ocjena;
	}

	public Integer getNavbarBackColorR() {
		return navbarBackColorR;
	}

	public void setNavbarBackColorR(Integer navbarBackColorR) {
		this.navbarBackColorR = navbarBackColorR;
	}

	public Integer getNavbarBackColorG() {
		return navbarBackColorG;
	}

	public void setNavbarBackColorG(Integer navbarBackColorG) {
		this.navbarBackColorG = navbarBackColorG;
	}

	public Integer getNavbarBackColorB() {
		return navbarBackColorB;
	}

	public void setNavbarBackColorB(Integer navbarBackColorB) {
		this.navbarBackColorB = navbarBackColorB;
	}

	public Integer getNavbarTextColorR() {
		return navbarTextColorR;
	}

	public void setNavbarTextColorR(Integer navbarTextColorR) {
		this.navbarTextColorR = navbarTextColorR;
	}

	public Integer getNavbarTextColorG() {
		return navbarTextColorG;
	}

	public void setNavbarTextColorG(Integer navbarTextColorG) {
		this.navbarTextColorG = navbarTextColorG;
	}

	public Integer getNavbarTextColorB() {
		return navbarTextColorB;
	}

	public void setNavbarTextColorB(Integer navbarTextColorB) {
		this.navbarTextColorB = navbarTextColorB;
	}

	public Integer getBodyBackColorR() {
		return bodyBackColorR;
	}

	public void setBodyBackColorR(Integer bodyBackColorR) {
		this.bodyBackColorR = bodyBackColorR;
	}

	public Integer getBodyBackColorG() {
		return bodyBackColorG;
	}

	public void setBodyBackColorG(Integer bodyBackColorG) {
		this.bodyBackColorG = bodyBackColorG;
	}

	public Integer getBodyBackColorB() {
		return bodyBackColorB;
	}

	public void setBodyBackColorB(Integer bodyBackColorB) {
		this.bodyBackColorB = bodyBackColorB;
	}

	public Integer getBodyTextColorR() {
		return bodyTextColorR;
	}

	public void setBodyTextColorR(Integer bodyTextColorR) {
		this.bodyTextColorR = bodyTextColorR;
	}

	public Integer getBodyTextColorG() {
		return bodyTextColorG;
	}

	public void setBodyTextColorG(Integer bodyTextColorG) {
		this.bodyTextColorG = bodyTextColorG;
	}

	public Integer getBodyTextColorB() {
		return bodyTextColorB;
	}

	public void setBodyTextColorB(Integer bodyTextColorB) {
		this.bodyTextColorB = bodyTextColorB;
	}

	public Integer getLinkColorR() {
		return linkColorR;
	}

	public void setLinkColorR(Integer linkColorR) {
		this.linkColorR = linkColorR;
	}

	public Integer getLinkColorG() {
		return linkColorG;
	}

	public void setLinkColorG(Integer linkColorG) {
		this.linkColorG = linkColorG;
	}

	public Integer getLinkColorB() {
		return linkColorB;
	}

	public void setLinkColorB(Integer linkColorB) {
		this.linkColorB = linkColorB;
	}

	public Integer getWellBackColorR() {
		return wellBackColorR;
	}

	public void setWellBackColorR(Integer wellBackColorR) {
		this.wellBackColorR = wellBackColorR;
	}

	public Integer getWellBackColorG() {
		return wellBackColorG;
	}

	public void setWellBackColorG(Integer wellBackColorG) {
		this.wellBackColorG = wellBackColorG;
	}

	public Integer getWellBackColorB() {
		return wellBackColorB;
	}

	public void setWellBackColorB(Integer wellBackColorB) {
		this.wellBackColorB = wellBackColorB;
	}

	public Integer getBodyTextSize() {
		return bodyTextSize;
	}

	public void setBodyTextSize(Integer bodyTextSize) {
		this.bodyTextSize = bodyTextSize;
	}

	public Integer getLeadTextSize() {
		return leadTextSize;
	}

	public void setLeadTextSize(Integer leadTextSize) {
		this.leadTextSize = leadTextSize;
	}

	public Integer getH1TextSize() {
		return h1TextSize;
	}

	public void setH1TextSize(Integer h1TextSize) {
		this.h1TextSize = h1TextSize;
	}

	public Integer getH2TextSize() {
		return h2TextSize;
	}

	public void setH2TextSize(Integer h2TextSize) {
		this.h2TextSize = h2TextSize;
	}

	public Integer getH4TextSize() {
		return h4TextSize;
	}

	public void setH4TextSize(Integer h4TextSize) {
		this.h4TextSize = h4TextSize;
	}

	public Integer getBodyFont() {
		return bodyFont;
	}

	public void setBodyFont(Integer bodyFont) {
		this.bodyFont = bodyFont;
	}

	public Integer gethPlusLeadFont() {
		return hPlusLeadFont;
	}

	public void sethPlusLeadFont(Integer hPlusLeadFont) {
		this.hPlusLeadFont = hPlusLeadFont;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bodyBackColorB == null) ? 0 : bodyBackColorB.hashCode());
		result = prime * result + ((bodyBackColorG == null) ? 0 : bodyBackColorG.hashCode());
		result = prime * result + ((bodyBackColorR == null) ? 0 : bodyBackColorR.hashCode());
		result = prime * result + ((bodyFont == null) ? 0 : bodyFont.hashCode());
		result = prime * result + ((bodyTextColorB == null) ? 0 : bodyTextColorB.hashCode());
		result = prime * result + ((bodyTextColorG == null) ? 0 : bodyTextColorG.hashCode());
		result = prime * result + ((bodyTextColorR == null) ? 0 : bodyTextColorR.hashCode());
		result = prime * result + ((bodyTextSize == null) ? 0 : bodyTextSize.hashCode());
		result = prime * result + ((h1TextSize == null) ? 0 : h1TextSize.hashCode());
		result = prime * result + ((h2TextSize == null) ? 0 : h2TextSize.hashCode());
		result = prime * result + ((h4TextSize == null) ? 0 : h4TextSize.hashCode());
		result = prime * result + ((hPlusLeadFont == null) ? 0 : hPlusLeadFont.hashCode());
		result = prime * result + ((leadTextSize == null) ? 0 : leadTextSize.hashCode());
		result = prime * result + ((linkColorB == null) ? 0 : linkColorB.hashCode());
		result = prime * result + ((linkColorG == null) ? 0 : linkColorG.hashCode());
		result = prime * result + ((linkColorR == null) ? 0 : linkColorR.hashCode());
		result = prime * result + ((navbarBackColorB == null) ? 0 : navbarBackColorB.hashCode());
		result = prime * result + ((navbarBackColorG == null) ? 0 : navbarBackColorG.hashCode());
		result = prime * result + ((navbarBackColorR == null) ? 0 : navbarBackColorR.hashCode());
		result = prime * result + ((navbarTextColorB == null) ? 0 : navbarTextColorB.hashCode());
		result = prime * result + ((navbarTextColorG == null) ? 0 : navbarTextColorG.hashCode());
		result = prime * result + ((navbarTextColorR == null) ? 0 : navbarTextColorR.hashCode());
		result = prime * result + ((ocjena == null) ? 0 : ocjena.hashCode());
		result = prime * result + ((wellBackColorB == null) ? 0 : wellBackColorB.hashCode());
		result = prime * result + ((wellBackColorG == null) ? 0 : wellBackColorG.hashCode());
		result = prime * result + ((wellBackColorR == null) ? 0 : wellBackColorR.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DesignProperties other = (DesignProperties) obj;
		if (bodyBackColorB == null) {
			if (other.bodyBackColorB != null)
				return false;
		} else if (!bodyBackColorB.equals(other.bodyBackColorB))
			return false;
		if (bodyBackColorG == null) {
			if (other.bodyBackColorG != null)
				return false;
		} else if (!bodyBackColorG.equals(other.bodyBackColorG))
			return false;
		if (bodyBackColorR == null) {
			if (other.bodyBackColorR != null)
				return false;
		} else if (!bodyBackColorR.equals(other.bodyBackColorR))
			return false;
		if (bodyFont == null) {
			if (other.bodyFont != null)
				return false;
		} else if (!bodyFont.equals(other.bodyFont))
			return false;
		if (bodyTextColorB == null) {
			if (other.bodyTextColorB != null)
				return false;
		} else if (!bodyTextColorB.equals(other.bodyTextColorB))
			return false;
		if (bodyTextColorG == null) {
			if (other.bodyTextColorG != null)
				return false;
		} else if (!bodyTextColorG.equals(other.bodyTextColorG))
			return false;
		if (bodyTextColorR == null) {
			if (other.bodyTextColorR != null)
				return false;
		} else if (!bodyTextColorR.equals(other.bodyTextColorR))
			return false;
		if (bodyTextSize == null) {
			if (other.bodyTextSize != null)
				return false;
		} else if (!bodyTextSize.equals(other.bodyTextSize))
			return false;
		if (h1TextSize == null) {
			if (other.h1TextSize != null)
				return false;
		} else if (!h1TextSize.equals(other.h1TextSize))
			return false;
		if (h2TextSize == null) {
			if (other.h2TextSize != null)
				return false;
		} else if (!h2TextSize.equals(other.h2TextSize))
			return false;
		if (h4TextSize == null) {
			if (other.h4TextSize != null)
				return false;
		} else if (!h4TextSize.equals(other.h4TextSize))
			return false;
		if (hPlusLeadFont == null) {
			if (other.hPlusLeadFont != null)
				return false;
		} else if (!hPlusLeadFont.equals(other.hPlusLeadFont))
			return false;
		if (leadTextSize == null) {
			if (other.leadTextSize != null)
				return false;
		} else if (!leadTextSize.equals(other.leadTextSize))
			return false;
		if (linkColorB == null) {
			if (other.linkColorB != null)
				return false;
		} else if (!linkColorB.equals(other.linkColorB))
			return false;
		if (linkColorG == null) {
			if (other.linkColorG != null)
				return false;
		} else if (!linkColorG.equals(other.linkColorG))
			return false;
		if (linkColorR == null) {
			if (other.linkColorR != null)
				return false;
		} else if (!linkColorR.equals(other.linkColorR))
			return false;
		if (navbarBackColorB == null) {
			if (other.navbarBackColorB != null)
				return false;
		} else if (!navbarBackColorB.equals(other.navbarBackColorB))
			return false;
		if (navbarBackColorG == null) {
			if (other.navbarBackColorG != null)
				return false;
		} else if (!navbarBackColorG.equals(other.navbarBackColorG))
			return false;
		if (navbarBackColorR == null) {
			if (other.navbarBackColorR != null)
				return false;
		} else if (!navbarBackColorR.equals(other.navbarBackColorR))
			return false;
		if (navbarTextColorB == null) {
			if (other.navbarTextColorB != null)
				return false;
		} else if (!navbarTextColorB.equals(other.navbarTextColorB))
			return false;
		if (navbarTextColorG == null) {
			if (other.navbarTextColorG != null)
				return false;
		} else if (!navbarTextColorG.equals(other.navbarTextColorG))
			return false;
		if (navbarTextColorR == null) {
			if (other.navbarTextColorR != null)
				return false;
		} else if (!navbarTextColorR.equals(other.navbarTextColorR))
			return false;
		if (ocjena == null) {
			if (other.ocjena != null)
				return false;
		} else if (!ocjena.equals(other.ocjena))
			return false;
		if (wellBackColorB == null) {
			if (other.wellBackColorB != null)
				return false;
		} else if (!wellBackColorB.equals(other.wellBackColorB))
			return false;
		if (wellBackColorG == null) {
			if (other.wellBackColorG != null)
				return false;
		} else if (!wellBackColorG.equals(other.wellBackColorG))
			return false;
		if (wellBackColorR == null) {
			if (other.wellBackColorR != null)
				return false;
		} else if (!wellBackColorR.equals(other.wellBackColorR))
			return false;
		return true;
	}

	public String generateCss() {
		String result = "";
		result += ".navbar-inverse{ background-color: rgb(" + navbarBackColorR + "," + navbarBackColorG + ","
				+ navbarBackColorB + "); border-color: rgb(" + navbarBackColorR + "," + navbarBackColorG + ","
				+ navbarBackColorB + ");}";
		result += ".navbar-inverse .navbar-nav>li>a:focus, .navbar-inverse .navbar-nav>li>a:hover, .navbar-inverse .navbar-nav>li>a, .navbar-inverse .navbar-brand:focus, .navbar-inverse .navbar-brand:hover, .navbar-inverse .navbar-brand{color: rgb("
				+ navbarTextColorR + "," + navbarTextColorG + "," + navbarTextColorB + ");}";
		result += "body{background-color: rgb(" + bodyBackColorR + "," + bodyBackColorG + "," + bodyBackColorB + ");}";
		result += "body{color: rgb(" + bodyTextColorR + "," + bodyTextColorG + "," + bodyTextColorB + ");}";
		result += "a, a:focus, a:hover{color: rgb(" + linkColorR + "," + linkColorG + "," + linkColorB + ");}";
		result += ".well{ border:none; background-color: rgb(" + wellBackColorR + "," + wellBackColorG + ","
				+ wellBackColorB + ");}";
		result += "body{ font-size: " + bodyTextSize + "px;}";
		result += "@media (min-width: 1px){ .lead { font-size: " + bodyTextSize + "px;}}";
		result += "h1{font-size:" + h1TextSize + "px;}";
		result += "h2{font-size:" + h2TextSize + "px;}";
		result += "h4{font-size:" + h4TextSize + "px;}";
		result += "body{font-family:" + toFont(bodyFont) + ";}";
		result += "h1, h2, h4, .lead{font-family:" + toFont(hPlusLeadFont) + ";}";
		result += "navbar a{font-size: 14px}";
		return result;
	}

	private String toFont(Integer number) {
		switch (number.intValue()) {
		case 0:
			return "'Roboto', sans-serif";
		case 1:
			return "'Open Sans', sans-serif";
		case 2:
			return "'Lato', sans-serif";
		case 3:
			return "'Slabo 27px', serif";
		case 4:
			return "'Oswald', sans-serif";
		case 5:
			return "'Montserrat', sans-serif";
		case 6:
			return "'Raleway', sans-serif";
		case 7:
			return "'Roboto Slab', serif";
		case 8:
			return "'Merriweather', serif";
		case 9:
			return "'Open Sans Condensed', sans-serif";
		}
		return "inherit";
	}

}
