package com.jades.codeal;

public class User {

	private int id;
	private String nom;
	private String prenom;
    private int nAdd;
	private String rue;
	private int cP;
	private String ville;
    private String email;
    private String mdp;
    private String photo;


	public User(){}

	public User(int id, String nom, String prenom, int nAdd, String rue, int cP, String ville, String email, String mdp, String photo){

		this.setId(id);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setNAdd(nAdd);
		this.setRue(rue);
        this.setCP(cP);
        this.setVille(ville);
        this.setEmail(email);
        this.setMdp(mdp);
        this.setPhoto(photo);
	}


    public User(String nom, String prenom){

        this.setNom(nom);
        this.setPrenom(prenom);
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getNAdd() {
		return nAdd;
	}

	public void setNAdd(int nAdd) {
		this.nAdd = nAdd;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

    public int getCP() {
        return cP;
    }

    public void setCP(int cP) {
        this.cP = cP;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }




}