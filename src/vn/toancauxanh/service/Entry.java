
package vn.toancauxanh.service;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Object;

import vn.greenglobal.front.service.HeaderService;
import vn.toancauxanh.cms.service.BannerService;
import vn.toancauxanh.cms.service.DanhMucService;
import vn.toancauxanh.cms.service.DonViHanhChinhService;
import vn.toancauxanh.cms.service.DonViService;
import vn.toancauxanh.cms.service.HoiDapService;
import vn.toancauxanh.cms.service.HomeService;
import vn.toancauxanh.cms.service.ImageService;
import vn.toancauxanh.cms.service.LanguageService;
import vn.toancauxanh.cms.service.LinhVucHoiDapService;
import vn.toancauxanh.cms.service.ThamSoService;
import vn.toancauxanh.cms.service.TinBaiService;
import vn.toancauxanh.model.VaiTro;

@Configuration
@Controller
public class Entry extends BaseObject<Object> {
	static Entry instance;

	@Value("${trangthai.apdung}")
	public String TT_AP_DUNG = "";
	@Value("${trangthai.daxoa}")
	public String TT_DA_XOA = "";
	@Value("${trangthai.khongapdung}")
	public String TT_KHONG_AP_DUNG = "";

	@Value("${trangthaitraloi.datraloi}")
	public String TT_DA_TRA_LOI = "";

	@Value("${trangthaitraloi.chuatraloi}")
	public String TT_CHUA_TRA_LOI = "";

	// No image url
	public String URL_M_NOIMAGE = "/assetsfe/images/lg_noimage.png";
	public String URL_S_NOIMAGE = "/assetsfe/images/sm_noimage.png";

	// trang thai bai viet
	@Value("${trangthaisoan.daduyet}")
	public String TTS_DA_DUYET = "";
	@Value("${trangthaisoan.dangsoan}")
	public String TTS_DANG_SOAN = "";
	@Value("${trangthaisoan.choduyet}")
	public String TTS_CHO_DUYET = "";
	@Value("${trangthaisoan.tuchoi}")
	public String TTS_TU_CHOI = "";
	@Value("${trangthaihienthi.tieudiemchinh}")
	public String TT_TIEU_DIEM_CHINH = "";
	@Value("${trangthaihienthi.noibat}")
	public boolean TT_NOI_BAT = true;

	@Value("${action.xem}")
	public String XEM = ""; // duoc xem bat ky
	@Value("${action.list}")
	public String LIST = ""; // duoc vao trang list search url
	@Value("${action.sua}")
	public String SUA = ""; // duoc sua bat ky
	@Value("${action.xoa}")
	public String XOA = ""; // duoc xoa bat ky
	@Value("${action.them}")
	public String THEM = ""; // duoc them
	@Value("${action.xuatban}")
	public String XUATBAN = ""; // duoc duyet va xuat ban
	@Value("${action.lichsucapnhat}")
	public String LICHSUCAPNHAT = ""; // duoc them
	@Value("${action.lichsu}")
	public String LICHSU = "";

	@Value("${url.vaitro}")
	public String VAITRO = "";

	@Value("${url.nguoidung}")
	public String NGUOIDUNG = "";

	@Value("${url.chudevideo}")
	public String CHUDEVIDEO = "";

	@Value("${url.video}")
	public String VIDEO = "";

	@Value("${url.thongbao}")
	public String THONGBAO = "";

	@Value("${url.banner}")
	public String BANNER = "";

	@Value("${url.chude}")
	public String CHUDE = "";

	@Value("${url.vanban}")
	public String VANBAN = "";

	@Value("${url.chude}")
	public String CATEGORY = "";

	@Value("${url.tinbai}")
	public String TINBAI = "";

	@Value("${url.linhvuc}")
	public String LINHVUC = "";

	@Value("${url.hoidap}")
	public String HOIDAP = "";

	@Value("${url.thamso}")
	public String THAMSO = "";

	// uend
	public char CHAR_CACH = ':';
	public String CACH = CHAR_CACH + "";

	// tham số
	@Value("${url.thamso}" + ":" + "${action.xem}")
	public String THAMSOXEM;
	@Value("${url.thamso}" + ":" + "${action.list}")
	public String THAMSOLIST = "";
	@Value("${url.thamso}" + ":" + "${action.sua}")
	public String THAMSOSUA = "";
	@Value("${url.thamso}" + ":" + "${action.them}")
	public String THAMSOTHEM = "";

	@Value("${url.vaitro}" + ":" + "${action.xem}")
	public String VAITROXEM;
	@Value("${url.vaitro}" + ":" + "${action.them}")
	public String VAITROTHEM = "";
	@Value("${url.vaitro}" + ":" + "${action.list}")
	public String VAITROLIST = "";
	@Value("${url.vaitro}" + ":" + "${action.xoa}")
	public String VAITROXOA = "";
	@Value("${url.vaitro}" + ":" + "${action.sua}")
	public String VAITROSUA = "";

	@Value("${url.nguoidung}" + ":" + "${action.xem}")
	public String NGUOIDUNGXEM = "";
	@Value("${url.nguoidung}" + ":" + "${action.them}")
	public String NGUOIDUNGTHEM = "";
	@Value("${url.nguoidung}" + ":" + "${action.list}")
	public String NGUOIDUNGLIST = "";
	@Value("${url.nguoidung}" + ":" + "${action.xoa}")
	public String NGUOIDUNGXOA = "";
	@Value("${url.nguoidung}" + ":" + "${action.sua}")
	public String NGUOIDUNGSUA = "";

	@Value("${url.hoidap}" + ":" + "${action.xem}")
	public String HOIDAPXEM = "";
	@Value("${url.hoidap}" + ":" + "${action.them}")
	public String HOIDAPTHEM = "";
	@Value("${url.hoidap}" + ":" + "${action.list}")
	public String HOIDAPLIST = "";
	@Value("${url.hoidap}" + ":" + "${action.xoa}")
	public String HOIDAPXOA = "";
	@Value("${url.hoidap}" + ":" + "${action.sua}")
	public String HOIDAPSUA = "";
	@Value("${url.hoidap}" + ":" + "${action.xuatban}")
	public String HOIDAPXUATBAN = "";

	@Value("${url.chudevideo}" + ":" + "${action.xem}")
	public String CHUDEVIDEOXEM = "";
	@Value("${url.chudevideo}" + ":" + "${action.them}")
	public String CHUDEVIDEOTHEM = "";
	@Value("${url.chudevideo}" + ":" + "${action.list}")
	public String CHUDEVIDEOLIST = "";
	@Value("${url.chudevideo}" + ":" + "${action.xoa}")
	public String CHUDEVIDEOXOA = "";
	@Value("${url.chudevideo}" + ":" + "${action.sua}")
	public String CHUDEVIDEOSUA = "";

	@Value("${url.video}" + ":" + "${action.xem}")
	public String VIDEOXEM = "";
	@Value("${url.video}" + ":" + "${action.them}")
	public String VIDEOTHEM = "";
	@Value("${url.video}" + ":" + "${action.list}")
	public String VIDEOLIST = "";
	@Value("${url.video}" + ":" + "${action.xoa}")
	public String VIDEOXOA = "";
	@Value("${url.video}" + ":" + "${action.sua}")
	public String VIDEOSUA = "";

	@Value("${url.thongbao}" + ":" + "${action.xem}")
	public String THONGBAOXEM = "";
	@Value("${url.thongbao}" + ":" + "${action.them}")
	public String THONGBAOTHEM = "";
	@Value("${url.thongbao}" + ":" + "${action.list}")
	public String THONGBAOLIST = "";
	@Value("${url.thongbao}" + ":" + "${action.xoa}")
	public String THONGBAOXOA = "";
	@Value("${url.thongbao}" + ":" + "${action.sua}")
	public String THONGBAOSUA = "";

	@Value("${url.banner}" + ":" + "${action.xem}")
	public String BANNERXEM = "";
	@Value("${url.banner}" + ":" + "${action.them}")
	public String BANNERTHEM = "";
	@Value("${url.banner}" + ":" + "${action.list}")
	public String BANNERLIST = "";
	@Value("${url.banner}" + ":" + "${action.xoa}")
	public String BANNERXOA = "";
	@Value("${url.banner}" + ":" + "${action.sua}")
	public String BANNERSUA = "";

	@Value("${url.vanban}" + ":" + "${action.xem}")
	public String VANBANXEM = "";
	@Value("${url.vanban}" + ":" + "${action.them}")
	public String VANBANTHEM = "";
	@Value("${url.vanban}" + ":" + "${action.list}")
	public String VANBANLIST = "";
	@Value("${url.vanban}" + ":" + "${action.xoa}")
	public String VANBANXOA = "";
	@Value("${url.vanban}" + ":" + "${action.sua}")
	public String VANBANSUA = "";

	@Value("${url.chude}" + ":" + "${action.xem}")
	public String CATEGORYXEM = "";
	@Value("${url.chude}" + ":" + "${action.them}")
	public String CATEGORYTHEM = "";
	@Value("${url.chude}" + ":" + "${action.list}")
	public String CATEGORYLIST = "";
	@Value("${url.chude}" + ":" + "${action.xoa}")
	public String CATEGORYXOA = "";
	@Value("${url.chude}" + ":" + "${action.sua}")
	public String CATEGORYSUA = "";

	@Value("${url.tinbai}" + ":" + "${action.xem}")
	public String BAIVIETXEM = "";
	@Value("${url.tinbai}" + ":" + "${action.them}")
	public String BAIVIETTHEM = "";
	@Value("${url.tinbai}" + ":" + "${action.list}")
	public String BAIVIETLIST = "";
	@Value("${url.tinbai}" + ":" + "${action.xoa}")
	public String BAIVIETXOA = "";
	@Value("${url.tinbai}" + ":" + "${action.sua}")
	public String BAIVIETSUA = "";
	@Value("${url.tinbai}" + ":" + "${action.xuatban}")
	public String BAIVIETXUATBAN = "";

	@Value("${url.quantrihethong}" + ":" + "${action.list}")
	public String QUANTRIHETHONGLIST = "";

	// Hệ thống active
	@Value("${url.hethong}" + ":" + "${action.xem}")
	public String HETHONGXEM = "";
	@Value("${url.hethong}" + ":" + "${action.sua}")
	public String HETHONGSUA = "";

	@Value("${url.thongke}" + ":" + "${action.list}")
	public String THONGKELIST = "";

	// aend
	public String[] getRESOURCES() {
		return new String[] { NGUOIDUNG, VAITRO, HOIDAP, CHUDEVIDEO, VIDEO, THONGBAO, BANNER, VANBAN, CATEGORY, TINBAI,
				CHUDE, LINHVUC, THAMSO };
	}

	public String[] getACTIONS() {
		return new String[] { LIST, XEM, THEM, SUA, XOA, XUATBAN, LICHSUCAPNHAT, LICHSU };
	}

	static {
		File file = new File(Labels.getLabel("filestore.root") + File.separator + Labels.getLabel("filestore.folder"));
		if (!file.exists()) {
			if (file.mkdir()) {
				System.out.println("Directory mis is created!");
			} else {
				System.out.println("Failed to create directory!");
			}
		}
	}
	@Autowired
	public Environment env;

	@Autowired
	DataSource dataSource;

	public Entry() {
		super();
		setCore();
		instance = this;
	}

	@Bean
	public FilterRegistrationBean cacheFilter() {
		FilterRegistrationBean rs = new FilterRegistrationBean(new CacheFilter());
		rs.addUrlPatterns("*.css");
		rs.addUrlPatterns("*.js");
		rs.addUrlPatterns("*.wpd");
		rs.addUrlPatterns("*.wcs");
		rs.addUrlPatterns("*.jpg");
		rs.addUrlPatterns("*.jpeg");
		rs.addUrlPatterns("*.png");
		rs.addUrlPatterns("*.svg");
		rs.addUrlPatterns("*.gif");
		return rs;
	}

	/*
	 * @Bean public FilterRegistrationBean loginFilter() {
	 * FilterRegistrationBean rs = new FilterRegistrationBean(new
	 * LoginFilter()); rs.addUrlPatterns("/cp*"); return rs; }
	 */

	Map<String, String> pathMap = new HashMap<>();
	{
		MapUtils.putAll(pathMap,
				new String[] { "doanhnghiep", "doanhnghiep", "gioithieu", "gioithieu", "cosophaply", "cosophaply" });
	}

	@RequestMapping(value = "/")
	public String home() {
		return "forward:/frontend/index.zhtml?&file=/frontend/home/home.zhtml";
	}

	@RequestMapping(value = "/{path:.+$}/{cat:\\d+}/id/{id:\\d+}")
	public String newDetail(@PathVariable String path, @PathVariable Long cat, @PathVariable Long id) {
		return "forward:/frontend/index.zhtml?resource=" + path + "&file=/frontend/kyhop/newdetail.zhtml&cat=" + cat
				+ "&id=" + id;
	}

	// BE
	@RequestMapping(value = "/cp")
	public String cp() {
		return "forward:/WEB-INF/zul/home1.zul?resource=chude&action=lietke&file=/WEB-INF/zul/chude/list.zul&macdinh=home";
	}

	@RequestMapping(value = "/cp/{path:.+$}")
	public String cp(@PathVariable String path) {
		return "forward:/WEB-INF/zul/home1.zul?resource=" + path + "&action=lietke&file=/WEB-INF/zul/" + path
				+ "/list.zul";
	}

	@RequestMapping(value = "/login")
	public String dangNhapBackend() {
		return "forward:/WEB-INF/zul/login.zul";
	}

	@RequestMapping(value = "/{path}")
	public String page(@PathVariable String path) {
		return "forward:/frontend/index.zhtml?&file=/frontend/" + path + "/home.zhtml";
	}

	@RequestMapping(value = "/{path}/{cat:\\d+}")
	public String getPage(@PathVariable String path, @PathVariable long cat) {
		return "forward:/frontend/index.zhtml?resource=" + path + "&file=/frontend/" + path + "/home.zhtml&cat=" + cat;
	}
	
	public final DonViService getDonVis() {
		return new DonViService();
	}

	public final HeaderService getHeaderService() {
		return new HeaderService();
	}

	public final LinhVucHoiDapService getLinhVucHoiDap() {
		return new LinhVucHoiDapService();
	}

	public final HoiDapService getHoiDap() {
		return new HoiDapService();
	}

	public final Quyen getQuyen() {
		return getNhanVien().getTatCaQuyen();
	}

	public final VaiTroService getVaiTros() {
		return new VaiTroService();
	}

	public final DonViHanhChinhService getDonViHanhChinhs() {
		return new DonViHanhChinhService();
	}

	public final TinBaiService getTinBais() {
		return new TinBaiService();
	}

	public final ImageService getImages() {
		return new ImageService();
	}

	public final HomeService getHomes() {
		return new HomeService();
	}

	public final ThamSoService getThamSos() {
		return new ThamSoService();
	}

	public final BannerService getBanners() {
		return new BannerService();
	}

	public final DanhMucService getDanhMuc() {
		return new DanhMucService();
	}

	public final List<String> getNoiDungActive() {
		return Arrays.asList("chude", "tinbai", "video", "gallery", "linhvuchoidap", "hoidaptructuyen", "faqcategory",
				"faq");
	}

	public final LanguageService getLanguages() {
		return new LanguageService();
	}

	public boolean checkVaiTro(String vaiTro) {
		if (vaiTro == null || vaiTro.isEmpty()) {
			return false;
		}
		boolean rs = false;
		for (VaiTro vt : getNhanVien().getVaiTros()) {
			if (vaiTro.equals(vt.getAlias())) {
				rs = true;
				break;
			}
		}
		return rs;// || getQuyen().get(vaiTro);
	}

	@Configuration
	@EnableWebMvc
	public static class MvcConfig extends WebMvcConfigurerAdapter {
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/files/**").addResourceLocations("file:/home/hdndhoavangdata/hdndfiles/");
			registry.addResourceHandler("/assetsfe/**").addResourceLocations("/assetsfe/");
			registry.addResourceHandler("/backend/**").addResourceLocations("/backend/");
			registry.addResourceHandler("/img/**").addResourceLocations("/img/");
			registry.addResourceHandler("/login/**").addResourceLocations("/login/");
		}

		@Override
		public void configureViewResolvers(final ViewResolverRegistry registry) {
			registry.jsp("/WEB-INF/", "*");
		}

		@ExceptionHandler(ResourceNotFoundException.class)
		@ResponseStatus(HttpStatus.NOT_FOUND)
		public String handleResourceNotFoundException() {
			return "forward:/WEB-INF/zul/notfound.zul";
		}
	}

}