Ext.define('uio.MenuedApp',
    			{
    				extend: 'Ext.panel.Panel',
    				xtype: 'menuedapp',
    				closable: true,
    				constructor: function(config) {

    					if (!config.noTitle)
						{
    						config.title = uio.formdefs[config.jclass].title ;
						}
    					
    					config.items = uio.formdefs[config.jclass].items;
    					config.buttons = uio.formdefs[config.jclass].buttons;

            	    	config.defaultType ='textfield';

            	    	config.url = '../../'+config.jclass+'/submit.htm';
    					this.callParent(arguments);
    			    },
    			    
    			}, function(){});
