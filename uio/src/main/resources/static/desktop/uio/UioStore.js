Ext.define('uio.UioStore',
    			{
    				extend: 'Ext.data.JsonStore',
    				xtype: 'uiostore',
    				constructor: function(config) {
    					config.fields = uio.formdefs[config.jclass].storeFields ;
    					config.autoLoad = true;
    					config.proxy = {
    					        type: 'ajax',
    					        url: config.url || '../../'+config.jclass+'/list.json',
    					        reader: {
    					            type: 'json',
    					            root: 'images',
    					            idProperty: uio.formdefs[config.jclass].idField
    					        }
    					    },
            	    	config.url = config.url || '../../'+config.jclass+'/list.json';
    					this.callParent(arguments);
    			    },
    				
    			}, function(){});
