
Ext.application({
	name : 'Dsquare',
	requires: ['uio.UioForm', 'uio.UioGrid'],
	launch : function() {
		
		
		var createViewport = function(tbar)
		{
			Ext.create('Ext.container.Viewport', {
				requires : [ 'Ext.rtl.*', '*', 'uio.FormPanel' ],
				rtl : true,
				layout : 'fit',
				items : [ {
					xtype : 'panel',
					id : 'bigpanel',
					items : [ {
						id : 'maintabpanel',
						xtype : 'tabpanel',
					} ],
					tbar : tbar,

				} ]

			});
		};
		
		function parse(o, deep)
		{
			deep = deep || false;
			if (Ext.isArray(o))
			{
				var r = [];
				for(var i=0; i < o.length; i++)
				{
					r.push(parse(o[i], deep));
				}
				return r;
			}
			var name = o.name;
			var type= o.type;
			
			var config = {xtype: deep ? 'menuitem' : 'button', text:name};
			if (type === "submenu")
			{
				config.menu = {items: parse(o.items, true)};
			}
			else if (type === "form")
			{
				config.listeners = {
						click: function(){
							var elem = {xtype: 'uioform', jclass: o.className, loadObject: o.loadObject, header:false};
							var windowConfig =  {layout:'fit', title: config.title, items: [elem]};
							windowConfig.shrinkWrap=3;
							var window = Ext.create('Ext.window.Window',windowConfig);						
							window.show();
							}
				};
			}
			else if (type === "grid")
			{
				config.listeners = {
						click: function(){
							config.header = false;
							var bbar = [];
							if (o.buttons)
							{
								for(var ii in o.buttons)
								{
									var button = o.buttons[ii];
									var buttonConfig = {xtype:'uiogridbutton', min:button.min, max:button.max, text: button.name, ajaxUrl: button.ajaxUrl, form: button.form, loadObject:button.loadObject};
									bbar.push(buttonConfig);
								}
							}
							
							var elem ={xtype: 'uiogrid', jclass: o.className, bbar: bbar};

							var windowConfig =  {layout:'fit', title: config.title, items: [elem]};
							windowConfig.height = 300;
							windowConfig.width = 300; 
							var window = Ext.create('Ext.window.Window',windowConfig);						
							window.show();
							}
				};
			}
			else if (type === "link")
			{
				config.href = o.url;
				config.hrefTarget = '_self';
			}
			else
			{
				console.log("Unknown type: %s", type);
			}
			return config;
		}

		Ext.Ajax.request(
				{
					url: 'menu.js',
					success: function(response, options)
					{
						var json = Ext.JSON.decode(response.responseText);
						var parsed = parse(json.items);
						createViewport(parsed);
					}
				});
	}
});
