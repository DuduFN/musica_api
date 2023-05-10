insert into tb_integrante(nome, funcao) values('David Gilmour', 'vocalista');
insert into tb_integrante(nome, funcao) values('Roger Waters', 'baixista');
insert into tb_integrante(nome, funcao) values('Richard Wright', 'tecladista');
insert into tb_integrante(nome, funcao) values('Richard Wright', 'tecladista');
insert into tb_integrante(nome, funcao) values('Nick Mason', 'baterista');
insert into tb_integrante(nome, funcao) values('Geezer Buttler', 'baixista');
insert into tb_integrante(nome, funcao) values('Tony Iommi', 'guitarrista');
insert into tb_integrante(nome, funcao) values('Ozzy Osbourne', 'vocalista');
insert into tb_integrante(nome, funcao) values('Bill Ward', 'baterista');

insert into tb_artista(nome, data_criacao, pais_origem) values('Pink Floyd', '1965-01-01', 'Inglaterra');
insert into tb_artista(nome, data_criacao, pais_origem) values('Black Sabbath', '1968-02-01', 'Inglaterra');
insert into tb_artista(nome, data_criacao, pais_origem) values('Queen', '1970-01-01', 'Inglaterra');

insert into tb_estudio(nome, local) values('EMI', 'Inglaterra');
insert into tb_estudio(nome, local) values('Island Studio', 'Inglaterra');

insert into tb_gravadora(nome, distribuidora) values('Harvest Records','Capitol Records');
insert into tb_gravadora(nome, distribuidora) values('Vertigo','EMI records');


insert into tb_album(data_lancamento, nome) values('1973-03-01', 'The dark side of the moon');
insert into tb_album(data_lancamento, nome) values('1970-09-18', 'Paranoid');
insert into tb_album(data_lancamento, nome) values('1986-06-02', 'A kind of Magic');

insert into tb_musica(ativo, duracao, data_lancamento, letra, nome)values('true', '00:06:53', '1973-02-01', 'Ticking away the moments that make up a dull day Fritter and waste the hours in an offhand way Kicking around on a piece of ground in your hometown Waiting for someone or something to show you the way Tired of lying in the sunshine, staying home to watch the rain You are young and life is long, and there is time to kill today And then one day you find ten years have got behind you No one told you when to run, you missed the starting gun And you run, and you run to catch up with the sun but it`s sinking Racing around to come up behind you again The sun is the same in a relative way but you`re older Shorter of breath and one day closer to death Every year is getting shorter, never seem to find the time Plans that either come to naught or half a page of scribbled lines Hanging on in quiet desperation is the English way The time is gone, the song is over, thought I`d something more to say Home, home again I like to be here when I can And when I come home cold and tired It`s good to warm my bones beside the fire Far away across the field The tolling of the iron bell Calls the faithful to their knees To hear the softly spoken magic spells', 'Time');
insert into tb_musica(ativo, duracao, data_lancamento, letra, nome)values('true', '00:06:22', '1973-06-23', 'Money Get away You get a good job with more pay and you`re okay Money It`s a gas Grab that cash with both hands and make a stash New car, caviar, four star, daydream Think I`ll buy me a football team Money Get back I`m alright, Jack, keep your hands off of my stack Money It`s a hit Don`t give me that do goody good bullshit I`m in the high-fidelity first-class traveling set And I think I need a Lear jet Money It`s a crime Share it fairly, but don`t take a slice of my pie Money So they say Is the root of all evil today But if you ask for a rise It`s no surprise that they`re giving none away Away, away, away Away, away, away I was in the right Yes, absolutely in the right I certainly was in the right Yeah, I was definitely in the right, that geezer was cruisin` for a bruisin` Yeah! Why does anyone do anything? I don`t know, I was really drunk at the time Just telling him it was in, he could get it in number two He was asking why it wasn`t coming up on freight eleven And after, I was yelling and screaming and telling him why It wasn`t coming up on freight eleven', 'Money');
insert into tb_musica(ativo, duracao, data_lancamento, letra, nome)values('true', '00:07:58', '1970-07-18', 'Generals gathered in their masses Just like witches at black masses Evil minds that plot destruction Sorcerer of death`s construction In the fields, the bodies burning As the war machine keeps turning Death and hatred to mankind Poisoning their brainwashed minds Oh lord, yeah! Politicians hide themselves away They only started the war Why should they go out to fight? They leave that role to the poor, yeah Time will tell on their power minds Making war just for fun Treating people just like pawns in chess Wait till their judgement day comes, yeah! Now in darkness, world stops turning Ashes where their bodies burning No more war pigs have the power Hand of God has struck the hour Day of judgement, God is calling On their knees, the war pigs crawling Begging mercy for their sins Satan laughing, spreads his wings Oh lord, yeah!', 'War Pigs');

insert into tb_playlist(nome, descricao) values('Playlist para dormir', 'Playlist com sons ralaxantes');
insert into tb_playlist(nome, descricao) values('Playlist para treinar', 'Playlist com sons animados');
insert into tb_playlist(nome, descricao) values('Playlist para viajarr', 'Playlist com sons de estrada');

insert into tb_musica_album(fk_album, fk_musica) values('1', '1');

insert into tb_integrante_artista(fk_integrante, fk_artista) values('1','1');
insert into tb_integrante_artista(fk_integrante, fk_artista) values('2','1');

insert into tb_musica_playlist(fk_playlist, fk_musica) values('1','1');
insert into tb_musica_playlist(fk_playlist, fk_musica) values('1','2');