class Robot {
	protected Case position;
	protected int reservoir;
	protected double vitesseForet;
	protected double vitesseEau;
	protected double vitesseRocher;
	protected double vitesseLibre;
	protected int quantiteEau;
	protected int tempsRemplissage;
	protected double extinction;
	

	public Robot(Case position,int reservoir,int vitesseForet,int vitesseEau,int vitesseRocher, int vitesseLibre, int tempsRemplissage, int 	quantiteEau, double extinction)
		{this.position=position;
		this.reservoir=reservoir;
		this.vitesseForet=vitesseForet;
		this.vitesseEau=vitesseEau;
		this.vitesseRocher=vitesseRocher;
		this.vitesseLibre=vitesseLibre;
		this.tempsRemplissage=tempsRemplissage;
		this.quantiteEau=quantiteEau;
		this.extinction=extinction;
		}


	public Case getPosition()
		{ return this.position;
		}
	
	public int getReservoir()
		{ return this.reservoir;
		}

	public int getQuantiteEau()
		{ return this.quantiteEau;
		}
	
	public int getTempsRemplissage()
		{ return this.tempsRemplissage;
		}

	public double getExtinction()
		{ return this.extinction;
		}
	

	public double getVitesse(NatureTerrain terrain)
		{if (terrain==NatureTerrain.EAU)
			{ return this.vitesseEau;
			}
		if (terrain==NatureTerrain.FORET)
			{return this.vitesseForet;
			}
		if (terrain==NatureTerrain.ROCHE)
			{return this.vitesseRocher;
			}
		if (terrain==TERRAIN_LIBRE || terrain==NatureTerrain.HABITAT)
			{ return this.vitesseLibre;
			}
		}

	public void setPosition(Case pos)
		// à voir si utilise une exception pour vérifier si la case existe...
		{ this.position=pos;
		}

	public void setQuantiteEau(int volume)
		{if(volume>this.reservoir)
			{System.out.println("Le volume dépasse la taille du reservoir !");
			}
		else
			{this.quantiteEau=volume;
			}
		}

	public void deverserEau(int volume)
		{if (volume>this.quantiteEau)
			{ System.out.println("Vous n'avez pas assez d'eau !");
			}
		else
			{ this.quantiteEau-=volume;
			}
		}

	public void remplirReservoir()
		{ if (this.position.getNature() != NatureTerrain.EAU)
			{ System.out.println("Remplissage impossible !");
			}
		else
			{ this.quantiteEau=this.reservoir;
			}
		}

	public void deplacer(Case dest)
		{ int xRob,yRob, xDest, yDest;
		NatureTerrain nature=dest.getNature();
		xDest=dest.getLigne();
		yDest=dest.getColonne();
		xRob=this.position.getLigne();
		yRob=this.position.getColonne();
		double vitesse=this.getVitesse(nature);
		if ( ((xDest+1==xRob) && (yDest==yRob)) || ((xDest-1==xRob) && (yDest==yRob)) || ((xDest==xRob) && (yDest+1==yRob)) || 			((xDest==xRob) && (yDest-1==yRob)))
			{ if(vitesse!=0)
				{this.position=dest;
				}
			else
				{System.out.println("Terrain non accessible !");
				}
			}
		else
			{System.out.println("Case non accessible !");
			}
		}
		
}		
		




	

	
		
